    @Test
    public void testAddArray_NullInput_SingleArray()
    {
        String arr[] = new String[] { "a" };
        Object list = LazyList.addArray(null,arr);
        assertNotNull(list);
        if(STRICT) {
            assertTrue(list instanceof List);
        }
        assertEquals(1, LazyList.size(list));
        assertEquals("a", LazyList.get(list,0));
    }
