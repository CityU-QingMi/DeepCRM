    @Test
    public void testAddArray_NonListInput_Array()
    {
        String input = "z";
        String arr[] = new String[] { "a", "b", "c" };
        Object list = LazyList.addArray(input,arr);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(4, LazyList.size(list));
        assertEquals("z", LazyList.get(list,0));
        assertEquals("a", LazyList.get(list,1));
        assertEquals("b", LazyList.get(list,2));
        assertEquals("c", LazyList.get(list,3));
    }
