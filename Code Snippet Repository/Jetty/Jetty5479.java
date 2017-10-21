    @Test
    public void testToString_GenericListInput()
    {
        List<String> input = new ArrayList<String>();
        input.add("a");

        assertEquals("[a]", LazyList.toString(input));

        input.add("b");
        input.add("c");

        assertEquals("[a, b, c]", LazyList.toString(input));
    }
