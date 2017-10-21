    @Test
    public void testGetListObject_NonListInput()
    {
        String input = "a";

        Object list = LazyList.getList(input);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(1, LazyList.size(list));
    }
