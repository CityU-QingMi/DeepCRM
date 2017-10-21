    @Test
    public void testConvert01() {
        final Map<String, String> map = new HashMap<>();
        map.put("test1", "another1");
        map.put("key2", "value2");

        final String converted = this.converter.convertToDatabaseColumn(map);

        assertNotNull("The converted value should not be null.", converted);

        final Map<String, String> reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", map, reversed);
    }
