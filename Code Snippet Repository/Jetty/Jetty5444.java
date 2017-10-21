    @Test
    public void testEnsureSize_NonListInput()
    {
        String input = "a";
        Object list = LazyList.ensureSize(input,10);
        assertNotNull(list);
        assertTrue(list instanceof List);
        // Not possible to test for List capacity value.
        assertEquals(1, LazyList.size(list));
        assertEquals("a", LazyList.get(list,0));
    }
