    @Test(expected = UnsupportedOperationException.class)
    public void testGetImmutableMapReturnsImmutableMapIfNonEmpty() {
        final DefaultThreadContextMap map = new DefaultThreadContextMap(true);
        map.put("key1", "value1");
        assertFalse(map.isEmpty());

        final Map<String, String> immutable = map.getImmutableMapOrNull();
        assertEquals("value1", immutable.get("key1")); // copy has values too

        // immutable
        immutable.put("key", "value"); // error
    }
