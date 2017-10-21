    @Test
    public void testAddObjectIntObject_LazyListInput()
    {
        Object list = LazyList.add(null, "c"); // [c]
        list=LazyList.add(list,0,"a"); // [a, c]
        list=LazyList.add(list,1,"b"); // [a, b, c]
        list=LazyList.add(list,3,"d"); // [a, b, c, d]

        assertEquals(4,LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));
        assertEquals("b",LazyList.get(list,1));
        assertEquals("c",LazyList.get(list,2));
        assertEquals("d",LazyList.get(list,3));
    }
