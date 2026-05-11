package net.kaelos.apexfusion.multiblock;

import java.util.Map;

public class MultiblockTemplate {
    private final int[] core_offset;
    private final String[][] pattern;
    private final Map<Character, String> dictionary;

    public MultiblockTemplate(int[] core_offset, String[][] pattern, Map<Character, String> dictionary) {
        this.core_offset = core_offset;
        this.pattern = pattern;
        this.dictionary = dictionary;
    }

    public int[] getCoreOffset() {
        return core_offset;
    }

    public String[][] getPattern() {
        return pattern;
    }

    public Map<Character, String> getDictionary() {
        return dictionary;
    }
}
