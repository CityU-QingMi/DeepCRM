    @Test
    public void testAddObjectObject_LazyListInput()
    {
        Object input = LazyList.add(null, "a");

        Object list = LazyList.add(input, "b");
        assertEquals(2,LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));

        list=LazyList.add(list, "c");
        assertEquals(3,LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));
        assertEquals("b",LazyList.get(list,1));
        assertEquals("c",LazyList.get(list,2));
    }
