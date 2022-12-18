package com.mango.bot.commands;

public enum Category {
    STAFF(":computer:", "Staff"),
    UTILITY(":tools:", "Utility"),
    FUN(":smile:", "Fun");
    public final String emoji;
    public final String name;
    Category(String emoji, String name){
        this.emoji = emoji;
        this.name = name;
    }
}
