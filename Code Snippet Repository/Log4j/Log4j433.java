    @Test
    public void testCountArgumentPlaceholders() throws Exception {
        assertEquals(0, ParameterFormatter.countArgumentPlaceholders(""));
        assertEquals(0, ParameterFormatter.countArgumentPlaceholders("aaa"));
        assertEquals(0, ParameterFormatter.countArgumentPlaceholders("\\{}"));
        assertEquals(1, ParameterFormatter.countArgumentPlaceholders("{}"));
        assertEquals(1, ParameterFormatter.countArgumentPlaceholders("{}\\{}"));
        assertEquals(2, ParameterFormatter.countArgumentPlaceholders("{}{}"));
        assertEquals(3, ParameterFormatter.countArgumentPlaceholders("{}{}{}"));
        assertEquals(4, ParameterFormatter.countArgumentPlaceholders("{}{}{}aa{}"));
        assertEquals(4, ParameterFormatter.countArgumentPlaceholders("{}{}{}a{]b{}"));
        assertEquals(5, ParameterFormatter.countArgumentPlaceholders("{}{}{}a{}b{}"));
    }
