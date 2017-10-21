    @Test
    public void testRemove() {
        final DefaultThreadContextMap map = createMap();
        assertEquals("value", map.get("key"));
        assertEquals("value2", map.get("key2"));

        map.remove("key");
        assertFalse(map.containsKey("key"));
        assertEquals("value2", map.get("key2"));
    }
