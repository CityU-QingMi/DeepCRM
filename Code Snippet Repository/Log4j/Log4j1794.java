    @Test
    public void testConvert01() {
        final StringMap map = new SortedArrayStringMap();
        map.putValue("test1", "another1");
        map.putValue("key2", "value2");

        final String converted = this.converter.convertToDatabaseColumn(map);

        assertNotNull("The converted value should not be null.", converted);

        final ReadOnlyStringMap reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", map, reversed);
    }
