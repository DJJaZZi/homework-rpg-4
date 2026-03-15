package com.narxoz.rpg.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartyComposite implements CombatNode {
    private final String name;
    private final List<CombatNode> children = new ArrayList<>();

    public PartyComposite(String name) {
        this.name = name;
    }

    public void add(CombatNode node) {
        children.add(node);
    }

    public void remove(CombatNode node) {
        children.remove(node);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return children.stream().mapToInt(CombatNode::getHealth).sum();
    }

    @Override
    public int getPower() {
        int totalPower = 0;
        for (CombatNode child : children) {
            if (child.isAlive()) {
                totalPower += child.getPower();
            }
        }
        return totalPower;
    }

    @Override
    public int getAttackPower() {
        return children.stream().mapToInt(CombatNode::getAttackPower).sum();
    }

    @Override
    public void takeDamage(int amount) {
        List<CombatNode> living = children.stream()
                .filter(CombatNode::isAlive)
                .toList();

        if (living.isEmpty()) return;

        int splitDamage = amount / living.size();
        for (CombatNode child : living) {
            child.takeDamage(splitDamage);
        }
    }

    @Override
    public boolean isAlive() {
        return children.stream().anyMatch(CombatNode::isAlive);
    }

    @Override
    public List<CombatNode> getChildren() {
        return children;
    }

    @Override
    public void printTree(String indent) {
        System.out.println(indent + "+ Party: " + name + " [Total HP=" + getHealth() + "]");
        for (CombatNode child : children) {
            child.printTree(indent + "  ");
        }
    }

    private List<CombatNode> getAliveChildren() {
        // TODO: helper for takeDamage()
        return new ArrayList<>();
    }
}
