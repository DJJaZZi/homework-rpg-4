package com.narxoz.rpg.composite;

import java.util.List;

public interface CombatNode {
    String getName();
    int getHealth();
    int getPower();
    int getAttackPower();
    void takeDamage(int amount);
    boolean isAlive();

    void add(CombatNode node);
    void remove(CombatNode node);
    List<CombatNode> getChildren();
    void printTree(String indent);
}
