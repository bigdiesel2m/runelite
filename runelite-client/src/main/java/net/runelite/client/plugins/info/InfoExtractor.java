/*
 * Copyright (c) 2020 BigDiesel2m
 * Copyright (c) 2020 andmcadams
 * Copyright (c) 2020 Abex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.info;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import lombok.Data;
import net.runelite.api.Client;
import net.runelite.api.Favour;
import net.runelite.api.GameState;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.VarPlayer;
import net.runelite.api.vars.AccountType;
import net.runelite.api.vars.SlayerUnlock;
import net.runelite.client.callback.ClientThread;

@Singleton
class InfoExtractor
{
	@Data
	private static class ExportData
	{
		private Map<Integer, QuestState> questStates;
		private Map<String, Integer> skillLevels;
		private Map<String, Integer> favour;
		private Map<SlayerUnlock, Boolean> slayerUnlocks;
		private int questPoints;
		private int combatLevel;
		private AccountType accountType;
	}

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	public void copyToClipboard()
	{
		clientThread.invokeLater(() ->
		{
			if (client.getGameState() != GameState.LOGGED_IN)
			{
				SwingUtilities.invokeLater(() ->
					JOptionPane.showMessageDialog((Component) client, "You must be logged in to export your account's data"));
				return;
			}

			ExportData data = collectData();

			String output = new Gson()
				.toJson(data);

			SwingUtilities.invokeLater(() ->
			{
				StringSelection stringselection = new StringSelection(output);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
			});
		});
	}

	private ExportData collectData()
	{
		ExportData d = new ExportData();

		d.questStates = Stream.of(Quest.values())
			.collect(ImmutableMap.toImmutableMap(Quest::getId, q -> q.getState(client)));
		d.skillLevels = Stream.of(Skill.values())
			.filter(i -> i != Skill.OVERALL)
			.collect(ImmutableMap.toImmutableMap(s -> s.name().toLowerCase(), client::getRealSkillLevel));
		d.favour = Stream.of(Favour.values())
			.collect(ImmutableMap.toImmutableMap(f -> f.getName().toLowerCase(), f -> client.getVar(f.getVarbit())));
		d.slayerUnlocks = Stream.of(SlayerUnlock.values())
			.collect(ImmutableMap.toImmutableMap(Function.identity(), u -> u.isEnabled(client)));

		d.questPoints = client.getVar(VarPlayer.QUEST_POINTS);
		d.combatLevel = client.getLocalPlayer().getCombatLevel();
		d.accountType = client.getAccountType();

		return d;
	}
}
