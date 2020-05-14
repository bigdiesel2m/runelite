package net.runelite.client.plugins.info;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Favour;
import net.runelite.api.Quest;
import net.runelite.api.Skill;
import net.runelite.api.VarPlayer;
import net.runelite.api.vars.SlayerUnlock;
import net.runelite.client.callback.ClientThread;
import com.google.gson.Gson;

public class InfoExtractor
{

	@com.google.inject.Inject
	private Gson gson;

	@Inject
	private ClientThread clientThread;


	public void doStuff (Client client)
	{
		clientThread.invokeLater(() ->
		{
			final HashMap<String, HashMap> output_dict = new HashMap<>();
			final HashMap<Integer, String> quest_dict = new HashMap<>();
			final HashMap<String, Integer> skill_dict = new HashMap<>();
			final HashMap<String, Integer> favour_dict = new HashMap<>();
			final HashMap<SlayerUnlock, Boolean> slayer_dict = new HashMap<>();
			final HashMap<String, Integer> other_dict = new HashMap<String, Integer>();

			//Quests
			for (Quest quest : Quest.values())
			{
				quest_dict.put(quest.getId(), quest.getState(client).name());
			}

			//Skills
			for (Skill skill : Skill.values())
			{
				skill_dict.put(skill.getName(), client.getRealSkillLevel(skill));
			}

			//Favour
			for (Favour favour : Favour.values())
			{
				favour_dict.put(favour.getName(), client.getVar(favour.getVarbit()));
			}

			//Slayer
			for (SlayerUnlock slayerunlock : SlayerUnlock.values())
			{
				slayer_dict.put(slayerunlock, slayerunlock.isEnabled(client));
			}


			//Other
			other_dict.put("QP", client.getVar(VarPlayer.QUEST_POINTS));
			other_dict.put("Combat", client.getLocalPlayer().getCombatLevel());
			other_dict.put("Iron", client.getAccountType().isIronman() ? 1 : 0);

			output_dict.put("Quests", quest_dict);
			output_dict.put("Skills", skill_dict);
			output_dict.put("Favour", favour_dict);
			output_dict.put("SlayerUnlocks", slayer_dict);
			output_dict.put("Other", other_dict);

			String output = gson.toJson(output_dict);
			StringSelection stringselection = new StringSelection(output);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
		});
	}
}
