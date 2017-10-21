    @Test
    public void testAddObjectIntObject_NonListInput_NonListItem()
    {
        String input = "a";
        String item = "b";

        Object list = LazyList.add(input, 0, item);
        assertNotNull(list);
        assertEquals(2, LazyList.size(list));
        assertEquals("b", LazyList.get(list,0));
        assertEquals("a", LazyList.get(list,1));
    }
