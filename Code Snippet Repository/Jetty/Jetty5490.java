    @Test
    public void testArray2List_MultiInput()
    {
        String input[] = new String[] { "a", "b", "c" };

        Object list = ArrayUtil.asMutableList(input);
        assertNotNull(list);
        assertTrue("Should be a List object", list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
        assertEquals("b", LazyList.get(list, 1));
        assertEquals("c", LazyList.get(list, 2));
    }
