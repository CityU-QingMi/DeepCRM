    @Test
    public void testPut() {
        final DefaultThreadContextMap map = new DefaultThreadContextMap(true);
        assertTrue(map.isEmpty());
        assertFalse(map.containsKey("key"));
        map.put("key", "value");

        assertFalse(map.isEmpty());
        assertTrue(map.containsKey("key"));
        assertEquals("value", map.get("key"));
    }
