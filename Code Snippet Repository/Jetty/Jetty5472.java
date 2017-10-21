    @Test
    public void testSize_GenericListInput()
    {
        List<String> input = new ArrayList<String>();

        assertEquals(0, LazyList.size(input));

        input.add("a");
        input.add("b");

        assertEquals(2, LazyList.size(input));

        input.add("c");

        assertEquals(3, LazyList.size(input));
    }
