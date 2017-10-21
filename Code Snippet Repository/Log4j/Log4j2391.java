    @Test
    public void testDefaultLayout_addsEachRowToTable() {
        final Text[][] values = {
                textArray(Help.Ansi.OFF, "a", "b", "c", "d"),
                textArray(Help.Ansi.OFF, "1", "2", "3", "4")
        };
        final int[] count = {0};
        TextTable tt = new TextTable(Help.Ansi.OFF) {
            @Override public void addRowValues(Text[] columnValues) {
                assertArrayEquals(values[count[0]], columnValues);
                count[0]++;
            }
        };
        Help.Layout layout = new Help.Layout(Help.defaultColorScheme(Help.Ansi.OFF), tt);
        layout.layout(null, values);
        assertEquals(2, count[0]);
    }
