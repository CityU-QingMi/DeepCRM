    @Test
    public void testConvert02() {
        Marker marker = MarkerManager.getMarker("anotherConvert02").setParents(MarkerManager.getMarker("finalConvert03"));
        marker = MarkerManager.getMarker("testConvert02").setParents(marker);

        final String converted = this.converter.convertToDatabaseColumn(marker);

        assertNotNull("The converted value should not be null.", converted);
        assertEquals("The converted value is not correct.", "testConvert02[ anotherConvert02[ finalConvert03 ] ]",
                converted);

        final Marker reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", "testConvert02", marker.getName());
        final Marker[] parents = marker.getParents();
        assertNotNull("The first parent should not be null.", parents);
        assertNotNull("The first parent should not be null.", parents[0]);
        assertEquals("The first parent is not correct.", "anotherConvert02", parents[0].getName());
        assertNotNull("The second parent should not be null.", parents[0].getParents());
        assertNotNull("The second parent should not be null.", parents[0].getParents()[0]);
        assertEquals("The second parent is not correct.", "finalConvert03", parents[0].getParents()[0].getName());
    }
