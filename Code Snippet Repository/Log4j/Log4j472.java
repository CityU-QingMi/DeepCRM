    @Test
    public void testGetCopyReturnsMutableMap() {
        final DefaultThreadContextMap map = new DefaultThreadContextMap(true);
        assertTrue(map.isEmpty());
        final Map<String, String> copy = map.getCopy();
        assertTrue(copy.isEmpty());

        copy.put("key", "value"); // mutable
        assertEquals("value", copy.get("key"));

        // thread context map not affected
        assertTrue(map.isEmpty());
    }
