    @Test
    public void testGetListObject_LazyListInput()
    {
        Object input = LazyList.add(null, "a");
        input = LazyList.add(input, "b");
        input = LazyList.add(input, "c");

        Object list = LazyList.getList(input);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
        assertEquals("b", LazyList.get(list, 1));
        assertEquals("c", LazyList.get(list, 2));
    }
