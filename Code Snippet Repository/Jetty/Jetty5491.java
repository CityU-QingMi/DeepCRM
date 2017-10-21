    @Test
    public void testArray2List_GenericsInput()
    {
        String input[] = new String[] { "a", "b", "c" };

        // Test the Generics definitions for array2List
        List<String> list = ArrayUtil.asMutableList(input);
        assertNotNull(list);
        assertTrue("Should be a List object", list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
        assertEquals("b", LazyList.get(list, 1));
        assertEquals("c", LazyList.get(list, 2));
    }
