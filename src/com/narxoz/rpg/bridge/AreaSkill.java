package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class AreaSkill extends Skill {
    public AreaSkill(String skillName, int basePower, EffectImplementor effect) {
        super(skillName, basePower, effect);
    }

    @Override
    public void cast(CombatNode target) {
        int damage = resolvedDamage();
        System.out.println("=> Casting [Area-of-Effect] " + getSkillName() + " (" + getEffectName() + ") on "
                + target.getName() + " for " + damage + " total damage.");

        target.takeDamage(damage);
    }
}
