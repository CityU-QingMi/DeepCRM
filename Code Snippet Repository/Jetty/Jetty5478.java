    @Test
    public void testToString_LazyListInput()
    {
        Object input = LazyList.add(null,"a");

        assertEquals("[a]", LazyList.toString(input));

        input = LazyList.add(input,"b");
        input = LazyList.add(input,"c");

        assertEquals("[a, b, c]", LazyList.toString(input));
    }
