    @Test
    public void testAddArray_NullInput_EmptyArray()
    {
        String arr[] = new String[0];
        Object list = LazyList.addArray(null,arr);
        if(STRICT) {
            assertNotNull(list);
            assertTrue(list instanceof List);
        }
    }
