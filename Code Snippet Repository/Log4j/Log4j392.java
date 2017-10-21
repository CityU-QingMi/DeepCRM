    @Test
    public void testPutAll() {
        ThreadContext.clearMap();
        //
        assertTrue(ThreadContext.isEmpty());
        assertFalse(ThreadContext.containsKey("key"));
        final int mapSize = 10;
        final Map<String, String> newMap = new HashMap<>(mapSize);
        for (int i = 1; i <= mapSize; i++) {
            newMap.put("key" + i, "value" + i);
        }
        ThreadContext.putAll(newMap);
        assertFalse(ThreadContext.isEmpty());
        for (int i = 1; i <= mapSize; i++) {
            assertTrue(ThreadContext.containsKey("key" + i));
            assertEquals("value" + i, ThreadContext.get("key" + i));
        }
    }
