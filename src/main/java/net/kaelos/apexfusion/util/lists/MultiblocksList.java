package net.kaelos.apexfusion.util.lists;

public enum MultiblocksList {
    COKE_OVEN("coke_oven");

    private String name;

    MultiblocksList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
