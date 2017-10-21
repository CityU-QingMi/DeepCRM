    @Test
    public void testTextTableAddsNewRowWhenAnyColumnTooLong() {
        TextTable table = new TextTable(Help.Ansi.OFF);
        table.addRowValues("*", "-c", ",",
                "--create, --create2, --create3, --create4, --create5, --create6, --create7, --create8",
                "description");
        assertEquals(String.format("" +
                        "* -c, --create, --create2, --create3, --create4, --create5, --create6,%n" +
                        "        --create7, --create8%n" +
                        "                              description%n"
                ,""), table.toString(new StringBuilder()).toString());

        table = new TextTable(Help.Ansi.OFF);
        table.addRowValues("", "-c", ",",
                "--create, --create2, --create3, --create4, --create5, --create6, --createAA7, --create8",
                "description");
        assertEquals(String.format("" +
                        "  -c, --create, --create2, --create3, --create4, --create5, --create6,%n" +
                        "        --createAA7, --create8%n" +
                        "                              description%n"
                ,""), table.toString(new StringBuilder()).toString());
    }
