    @Test
    public void testDoesNothingIfConstructedWithUseMapIsFalse() {
        final DefaultThreadContextMap map = new DefaultThreadContextMap(false);
        assertTrue(map.isEmpty());
        assertFalse(map.containsKey("key"));
        map.put("key", "value");

        assertTrue(map.isEmpty());
        assertFalse(map.containsKey("key"));
        assertNull(map.get("key"));
    }
