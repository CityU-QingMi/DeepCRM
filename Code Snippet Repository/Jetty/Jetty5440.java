    @Test
    public void testAddArray_GenericListInput_NullArray()
    {
        List<String> input = new ArrayList<String>();
        input.add("x");
        input.add("y");
        input.add("z");

        String arr[] = null;
        Object list = LazyList.addArray(input,arr);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("x", LazyList.get(list,0));
        assertEquals("y", LazyList.get(list,1));
        assertEquals("z", LazyList.get(list,2));
    }
