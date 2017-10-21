    public void testConvertChar() {
        assertEquals(new Character('A'), converter.convertValue(context, "A", char.class));
        assertEquals(new Character('Z'), converter.convertValue(context, "Z", char.class));
        assertEquals(new Character('A'), converter.convertValue(context, "A", Character.class));
        assertEquals(new Character('Z'), converter.convertValue(context, "Z", Character.class));

        assertEquals(new Character('A'), converter.convertValue(context, new Character('A'), char.class));
        assertEquals(new Character('Z'), converter.convertValue(context, new Character('Z'), char.class));
        assertEquals(new Character('A'), converter.convertValue(context, new Character('A'), Character.class));
        assertEquals(new Character('Z'), converter.convertValue(context, new Character('Z'), Character.class));

        assertEquals(new Character('D'), converter.convertValue(context, "DEF", char.class));
        assertEquals(new Character('X'), converter.convertValue(context, "XYZ", Character.class));
        assertEquals(new Character(' '), converter.convertValue(context, " ", Character.class));
        assertEquals(new Character(' '), converter.convertValue(context, "   ", char.class));

        assertEquals(null, converter.convertValue(context, "", char.class));
    }
