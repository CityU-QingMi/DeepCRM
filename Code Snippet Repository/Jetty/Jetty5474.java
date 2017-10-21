    @Test
    public void testContains_LazyListInput()
    {
        Object input = LazyList.add(null,"a");
        input = LazyList.add(input,"b");
        input = LazyList.add(input,"c");

        assertFalse(LazyList.contains(input, "z"));
        assertTrue(LazyList.contains(input, "a"));
        assertTrue(LazyList.contains(input, "b"));
    }
