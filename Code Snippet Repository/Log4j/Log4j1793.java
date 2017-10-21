    @Test
    public void testConvertToDatabaseColumn02() {
        final StringMap map = new SortedArrayStringMap();
        map.putValue("someKey", "coolValue");
        map.putValue("anotherKey", "testValue");
        map.putValue("myKey", "yourValue");

        assertEquals("The converted value is not correct.", map.toString(),
                this.converter.convertToDatabaseColumn(map));
    }
