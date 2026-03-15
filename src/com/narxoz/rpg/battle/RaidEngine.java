package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;

import java.util.Random;

public class RaidEngine {
    private Random random = new Random(1L);

    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB, Skill teamASkill, Skill teamBSkill) {
        RaidResult result = new RaidResult();

        if(teamA == null || teamB == null || teamASkill == null || teamBSkill == null){
            result.setWinner("Error: Missing combatants or skills");
            return result;
        }

        int rounds = 0;
        int maxRounds = 100;

        result.addLine("--- RAID START ---");
        result.addLine(teamA.getName() + " VS " + teamB.getName());

        while(teamA.isAlive() && teamB.isAlive() && rounds < maxRounds){
            rounds++;
            result.addLine("\\n[Round \" + rounds + \"]");

            boolean critA = random.nextInt(100) < 20;
            result.addLine(teamA.getName() + " uses " + teamASkill.getSkillName() + (critA ? " (CRITICAL HIT!)" : ""));
            teamASkill.cast(teamB);
            if(critA) teamASkill.cast(teamB);
            result.addLine(teamB.getName() + " remaining HP: " + teamB.getHealth());

            if (!teamB.isAlive()) {
                result.addLine(teamB.getName() + " has been defeated!");
                break;
            }

            boolean critB = random.nextInt(100) < 20;
            result.addLine(teamB.getName() + " uses " + teamBSkill.getSkillName() + (critB ? " (CRITICAL HIT!)" : ""));

            teamBSkill.cast(teamA);
            if (critB) teamBSkill.cast(teamA);

            result.addLine(teamA.getName() + " remaining HP: " + teamA.getHealth());

            if (!teamA.isAlive()) {
                result.addLine(teamA.getName() + " has been defeated!");
                break;
            }

        }

        result.setRounds(rounds);
        if (teamA.isAlive() && !teamB.isAlive()) {
            result.setWinner(teamA.getName());
        } else if (teamB.isAlive() && !teamA.isAlive()) {
            result.setWinner(teamB.getName());
        } else {
            result.setWinner("Draw / Timeout");
        }

        return result;
    }
}
