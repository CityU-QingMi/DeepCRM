    @Test
    public void testAddObjectIntObject_NullInput_NonListItem2()
    {
        Assume.assumeTrue(STRICT); // Only run in STRICT mode.

        String item = "a";
        // Test branch of logic "index>0"
        Object list = LazyList.add(null, 1, item); // Always throws exception?
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(1,LazyList.size(list));
    }
