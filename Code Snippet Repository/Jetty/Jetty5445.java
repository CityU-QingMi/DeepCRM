    @Test
    public void testEnsureSize_LazyListInput()
    {
        Object input = LazyList.add(null, "a");
        input = LazyList.add(input,"b");

        Object list = LazyList.ensureSize(input,10);
        assertNotNull(list);
        assertTrue(list instanceof List);
        // Not possible to test for List capacity value.
        assertEquals(2, LazyList.size(list));
        assertEquals("a", LazyList.get(list,0));
        assertEquals("b", LazyList.get(list,1));
    }
