    public void testFloatValues() {
        NumberConverter numberConverter = new NumberConverter();

        assertTrue(numberConverter.isInRange(-1.65, "-1.65", Float.class));
        assertTrue(numberConverter.isInRange(1.9876, "1.9876", float.class));

        Float value = (Float) basicConverter.convertValue("-1.444401", Float.class);
        assertNotNull(value);
        assertEquals(Float.valueOf("-1.444401"), value);

        value = (Float) basicConverter.convertValue("1.46464989", Float.class);
        assertNotNull(value);
        assertEquals(Float.valueOf(1.46464989f), value);
    }
