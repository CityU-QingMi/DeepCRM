    @Test
    public void testAddArray_NonListInput_NullArray()
    {
        String input = "z";
        String arr[] = null;
        Object list = LazyList.addArray(input,arr);
        assertNotNull(list);
        if(STRICT) {
            assertTrue(list instanceof List);
        }
        assertEquals(1, LazyList.size(list));
        assertEquals("z", LazyList.get(list,0));
    }
