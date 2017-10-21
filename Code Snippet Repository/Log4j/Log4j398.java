    public static void testGetContextReturnsMutableCopy() {
        ThreadContext.clearMap();
        final Map<String, String> map1 = ThreadContext.getContext();
        assertTrue(map1.isEmpty());
        map1.put("K", "val"); // no error
        assertEquals("val", map1.get("K"));

        // adding to copy does not affect thread context map
        assertTrue(ThreadContext.getContext().isEmpty());

        ThreadContext.put("key", "val2");
        final Map<String, String> map2 = ThreadContext.getContext();
        assertEquals(1, map2.size());
        assertEquals("val2", map2.get("key"));
        map2.put("K", "val"); // no error
        assertEquals("val", map2.get("K"));

        // first copy is not affected
        assertNotSame(map1, map2);
        assertEquals(1, map1.size());
    }
