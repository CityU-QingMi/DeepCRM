    @Test
    public void testAddArray_NullInput_Array()
    {
        String arr[] = new String[] { "a", "b", "c" };
        Object list = LazyList.addArray(null,arr);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("a", LazyList.get(list,0));
        assertEquals("b", LazyList.get(list,1));
        assertEquals("c", LazyList.get(list,2));
    }
