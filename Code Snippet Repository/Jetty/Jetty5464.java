    @Test
    public void testGetListObject_LinkedListInput()
    {
        List<String> input = new LinkedList<String>();
        input.add("a");
        input.add("b");
        input.add("c");

        Object list = LazyList.getList(input);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
        assertEquals("b", LazyList.get(list, 1));
        assertEquals("c", LazyList.get(list, 2));
    }
