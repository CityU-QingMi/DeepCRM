    @Test
    public void testAddArray_GenericListInput_SingleArray()
    {
        List<String> input = new ArrayList<String>();
        input.add("x");
        input.add("y");
        input.add("z");

        String arr[] = new String[] { "a" };
        Object list = LazyList.addArray(input,arr);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(4, LazyList.size(list));
        assertEquals("x", LazyList.get(list,0));
        assertEquals("y", LazyList.get(list,1));
        assertEquals("z", LazyList.get(list,2));
        assertEquals("a", LazyList.get(list,3));
    }
