    @Test
    public void testAddArray_LazyListInput_NullArray()
    {
        Object input = LazyList.add(null,"x");
        input = LazyList.add(input,"y");
        input = LazyList.add(input,"z");

        String arr[] = null;
        Object list = LazyList.addArray(input,arr);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("x", LazyList.get(list,0));
        assertEquals("y", LazyList.get(list,1));
        assertEquals("z", LazyList.get(list,2));
    }
