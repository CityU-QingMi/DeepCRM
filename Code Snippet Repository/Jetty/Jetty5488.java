    @Test
    public void testArray2List_EmptyInput()
    {
        String input[] = new String[0];

        Object list = ArrayUtil.asMutableList(input);
        assertNotNull(list);
        assertTrue("Should be a List object", list instanceof List);
        assertEquals(0, LazyList.size(list));
    }
