    @Test
    public void testAddArray_LazyListInput_Array()
    {
        Object input = LazyList.add(null,"x");
        input = LazyList.add(input,"y");
        input = LazyList.add(input,"z");

        String arr[] = new String[] { "a", "b", "c" };
        Object list = LazyList.addArray(input,arr);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(6, LazyList.size(list));
        assertEquals("x", LazyList.get(list,0));
        assertEquals("y", LazyList.get(list,1));
        assertEquals("z", LazyList.get(list,2));
        assertEquals("a", LazyList.get(list,3));
        assertEquals("b", LazyList.get(list,4));
        assertEquals("c", LazyList.get(list,5));
    }
