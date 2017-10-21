    @Test
    public void testGetListObject_NullInput()
    {
        Object input = null;

        Object list = LazyList.getList(input);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(0, LazyList.size(list));
    }
