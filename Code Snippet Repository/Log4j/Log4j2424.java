    @Test
    public void testTextTable() {
        TextTable table = new TextTable(Help.Ansi.OFF);
        table.addRowValues(textArray(Help.Ansi.OFF, "", "-v", ",", "--verbose", "show what you're doing while you are doing it"));
        table.addRowValues(textArray(Help.Ansi.OFF, "", "-p", null, null, "the quick brown fox jumped over the lazy dog. The quick brown fox jumped over the lazy dog."));
        assertEquals(String.format(
                "  -v, --verbose               show what you're doing while you are doing it%n" +
                "  -p                          the quick brown fox jumped over the lazy dog. The%n" +
                "                                quick brown fox jumped over the lazy dog.%n"
                ,""), table.toString(new StringBuilder()).toString());
    }
