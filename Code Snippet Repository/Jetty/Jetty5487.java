    @Test
    public void testArray2List_NullInput()
    {
        Object input[] = null;

        Object list = ArrayUtil.asMutableList(input);
        assertNotNull(list);
        assertTrue("Should be a List object", list instanceof List);
        assertEquals(0, LazyList.size(list));
    }
