    @Test
    public void testRemoveAll() {
        ThreadContext.clearMap();
        ThreadContext.put("testKey1", "testValue1");
        ThreadContext.put("testKey2", "testValue2");
        assertEquals("testValue1", ThreadContext.get("testKey1"));
        assertEquals("testValue2", ThreadContext.get("testKey2"));
        assertFalse(ThreadContext.isEmpty());

        ThreadContext.removeAll(Arrays.asList("testKey1", "testKey2"));
        assertNull(ThreadContext.get("testKey1"));
        assertNull(ThreadContext.get("testKey2"));
        assertTrue(ThreadContext.isEmpty());
    }
