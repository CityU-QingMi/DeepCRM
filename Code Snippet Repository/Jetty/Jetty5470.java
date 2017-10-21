    @Test
    public void testSize_LazyListInput()
    {
        Object input = LazyList.add(null,"a");
        input = LazyList.add(input,"b");

        assertEquals(2, LazyList.size(input));

        input = LazyList.add(input,"c");

        assertEquals(3, LazyList.size(input));
    }
