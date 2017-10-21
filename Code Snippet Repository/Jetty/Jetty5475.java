    @Test
    public void testContains_GenericListInput()
    {
        List<String> input = new ArrayList<String>();
        input.add("a");
        input.add("b");
        input.add("c");

        assertFalse(LazyList.contains(input, "z"));
        assertTrue(LazyList.contains(input, "a"));
        assertTrue(LazyList.contains(input, "b"));
    }
