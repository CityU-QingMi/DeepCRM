    @Test(expected = IllegalArgumentException.class)
    public void testTextTableAddsNewRowWhenTooManyValuesSpecified() {
        TextTable table = new TextTable(Help.Ansi.OFF);
        table.addRowValues(textArray(Help.Ansi.OFF, "", "-c", ",", "--create", "description", "INVALID", "Row 3"));
//        assertEquals(String.format("" +
//                        "  -c, --create                description                                       %n" +
//                        "                                INVALID                                         %n" +
//                        "                                Row 3                                           %n"
//                ,""), table.toString(new StringBuilder()).toString());
    }
