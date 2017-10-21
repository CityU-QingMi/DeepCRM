    @Test
    public void testConvertToDatabaseColumn02() {
        final Map<String, String> map = new HashMap<>();
        map.put("someKey", "coolValue");
        map.put("anotherKey", "testValue");
        map.put("myKey", "yourValue");

        assertEquals("The converted value is not correct.", map.toString(),
                this.converter.convertToDatabaseColumn(map));
    }
