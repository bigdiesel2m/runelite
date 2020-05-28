/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.api;

import lombok.RequiredArgsConstructor;

/**
 * An enumeration of different prayer spells.
 */
@RequiredArgsConstructor
public enum Prayer
{
	/**
	 * Thick Skin (Level 1, Defence).
	 */
	THICK_SKIN(Varbits.PRAYER_THICK_SKIN, 5.0, 0),
	/**
	 * Burst of Strength (Level 4, Strength).
	 */
	BURST_OF_STRENGTH(Varbits.PRAYER_BURST_OF_STRENGTH, 5.0, 1),
	/**
	 * Clarity of Thought (Level 7, Attack).
	 */
	CLARITY_OF_THOUGHT(Varbits.PRAYER_CLARITY_OF_THOUGHT, 5.0, 2),
	/**
	 * Sharp Eye (Level 8, Ranging).
	 */
	SHARP_EYE(Varbits.PRAYER_SHARP_EYE, 5.0, 18),
	/**
	 * Mystic Will (Level 9, Magic).
	 */
	MYSTIC_WILL(Varbits.PRAYER_MYSTIC_WILL, 5.0, 19),
	/**
	 * Rock Skin (Level 10, Defence).
	 */
	ROCK_SKIN(Varbits.PRAYER_ROCK_SKIN, 10.0, 3),
	/**
	 * Superhuman Strength (Level 13, Strength).
	 */
	SUPERHUMAN_STRENGTH(Varbits.PRAYER_SUPERHUMAN_STRENGTH, 10.0, 4),
	/**
	 * Improved Reflexes (Level 16, Attack).
	 */
	IMPROVED_REFLEXES(Varbits.PRAYER_IMPROVED_REFLEXES, 10.0, 5),
	/**
	 * Rapid Restore (Level 19, Stats).
	 */
	RAPID_RESTORE(Varbits.PRAYER_RAPID_RESTORE, 60.0 / 36.0, 6),
	/**
	 * Rapid Heal (Level 22, Hitpoints).
	 */
	RAPID_HEAL(Varbits.PRAYER_RAPID_HEAL, 60.0 / 18, 7),
	/**
	 * Protect Item (Level 25).
	 */
	PROTECT_ITEM(Varbits.PRAYER_PROTECT_ITEM, 60.0 / 18, 8),
	/**
	 * Hawk Eye (Level 26, Ranging).
	 */
	HAWK_EYE(Varbits.PRAYER_HAWK_EYE, 10.0, 20),
	/**
	 * Mystic Lore (Level 27, Magic).
	 */
	MYSTIC_LORE(Varbits.PRAYER_MYSTIC_LORE, 10.0, 21),
	/**
	 * Steel Skin (Level 28, Defence).
	 */
	STEEL_SKIN(Varbits.PRAYER_STEEL_SKIN, 20.0, 9),
	/**
	 * Ultimate Strength (Level 31, Strength).
	 */
	ULTIMATE_STRENGTH(Varbits.PRAYER_ULTIMATE_STRENGTH, 20.0, 10),
	/**
	 * Incredible Reflexes (Level 34, Attack).
	 */
	INCREDIBLE_REFLEXES(Varbits.PRAYER_INCREDIBLE_REFLEXES, 20.0, 11),
	/**
	 * Protect from Magic (Level 37).
	 */
	PROTECT_FROM_MAGIC(Varbits.PRAYER_PROTECT_FROM_MAGIC, 20.0, 12),
	/**
	 * Protect from Missiles (Level 40).
	 */
	PROTECT_FROM_MISSILES(Varbits.PRAYER_PROTECT_FROM_MISSILES, 20.0, 13),
	/**
	 * Protect from Melee (Level 43).
	 */
	PROTECT_FROM_MELEE(Varbits.PRAYER_PROTECT_FROM_MELEE, 20.0, 14),
	/**
	 * Eagle Eye (Level 44, Ranging).
	 */
	EAGLE_EYE(Varbits.PRAYER_EAGLE_EYE, 20.0, 22),
	/**
	 * Mystic Might (Level 45, Magic).
	 */
	MYSTIC_MIGHT(Varbits.PRAYER_MYSTIC_MIGHT, 20.0, 23),
	/**
	 * Retribution (Level 46).
	 */
	RETRIBUTION(Varbits.PRAYER_RETRIBUTION, 5.0, 15),
	/**
	 * Redemption (Level 49).
	 */
	REDEMPTION(Varbits.PRAYER_REDEMPTION, 10.0, 16),
	/**
	 * Smite (Level 52).
	 */
	SMITE(Varbits.PRAYER_SMITE, 30.0, 17),
	/**
	 * Chivalry (Level 60, Defence/Strength/Attack).
	 */
	CHIVALRY(Varbits.PRAYER_CHIVALRY, 40.0, 25),
	/**
	 * Piety (Level 70, Defence/Strength/Attack).
	 */
	PIETY(Varbits.PRAYER_PIETY, 40.0, 26),
	/**
	 * Preserve (Level 55).
	 */
	PRESERVE(Varbits.PRAYER_PRESERVE, 60.0 / 18, 28),
	/**
	 * Rigour (Level 74, Ranging/Damage/Defence).
	 */
	RIGOUR(Varbits.PRAYER_RIGOUR, 40.0, 24),
	/**
	 * Augury (Level 77, Magic/Magic Def./Defence).
	 */
	AUGURY(Varbits.PRAYER_AUGURY, 40.0, 27);

	private final Varbits varbit;
	private final double drainRate;
	private final int id;

	/**
	 * Gets the varbit that stores whether the prayer is active or not.
	 *
	 * @return the prayer active varbit
	 */
	public Varbits getVarbit()
	{
		return varbit;
	}

	/**
	 * Gets the prayer drain rate (measured in pray points/minute)
	 *
	 * @return the prayer drain rate
	 */
	public double getDrainRate()
	{
		return drainRate;
	}

	public boolean isUnlocked(Client client)
	{
		client.runScript(ScriptID.PRAYER_ISAVAILABLE, id);
		return client.getIntStack()[0] != 0;
	}
}
