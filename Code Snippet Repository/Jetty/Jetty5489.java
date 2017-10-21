    @Test
    public void testArray2List_SingleInput()
    {
        String input[] = new String[] { "a" };

        Object list = ArrayUtil.asMutableList(input);
        assertNotNull(list);
        assertTrue("Should be a List object", list instanceof List);
        assertEquals(1, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
    }
