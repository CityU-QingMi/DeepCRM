    @Test
    public void testConvert01() {
        final Marker marker = MarkerManager.getMarker("testConvert01");

        final String converted = this.converter.convertToDatabaseColumn(marker);

        assertNotNull("The converted value should not be null.", converted);
        assertEquals("The converted value is not correct.", "testConvert01", converted);

        final Marker reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", "testConvert01", marker.getName());
    }
