    @Test
    public void testAddArray_NonListInput_SingleArray()
    {
        String input = "z";
        String arr[] = new String[] { "a" };
        Object list = LazyList.addArray(input,arr);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(2, LazyList.size(list));
        assertEquals("z", LazyList.get(list,0));
        assertEquals("a", LazyList.get(list,1));
    }
