package com.narxoz.rpg.composite;

import java.util.Collections;
import java.util.List;

public abstract class UnitLeaf implements CombatNode {
    private final String name;
    private int health;
    protected int power;
    private final int attackPower;

    protected UnitLeaf(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttackPower() {
        return isAlive() ? attackPower : 0;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void add(CombatNode node) {
        throw new UnsupportedOperationException("Cannot add a node to a single unit.");
    }

    @Override
    public void remove(CombatNode node) {
        throw new UnsupportedOperationException("Cannot remove a node from a single unit.");
    }

    @Override
    public List<CombatNode> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void printTree(String indent) {
        System.out.println(indent + "- " + name + " [HP=" + health + ", ATK=" + attackPower + "]");
    }
}
