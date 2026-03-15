package com.narxoz.rpg.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaidGroup implements CombatNode {
    private final String name;
    private final List<CombatNode> children = new ArrayList<>();

    public RaidGroup(String name) {
        this.name = name;
    }

    @Override
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
        return 0;
    }

    @Override
    public int getAttackPower() {
        return getAliveChildren().stream().mapToInt(CombatNode::getAttackPower).sum();
    }

    @Override
    public void takeDamage(int amount) {
        List<CombatNode> alive = getAliveChildren();
        if (alive.isEmpty()) return;

        int splitDamage = amount / alive.size();
        for (CombatNode child : alive) {
            child.takeDamage(splitDamage);
        }
    }

    @Override
    public boolean isAlive() {
        return children.stream().anyMatch(CombatNode::isAlive);
    }


    @Override
    public List<CombatNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void printTree(String indent) {]
        System.out.println(indent + "== RAID: " + name + " [Total HP=" + getHealth() + ", Total ATK=" + getAttackPower() + "] ==");
        for (CombatNode child : children) {
            child.printTree(indent + "  ");
        }
    }

    private List<CombatNode> getAliveChildren() {
        return children.stream()
                .filter(CombatNode::isAlive)
                .toList();
    }
}
