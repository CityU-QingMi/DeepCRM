    @Test
    public void testAddObjectObject_AddNull()
    {
        Object list=null;
        list=LazyList.add(list, null);
        assertEquals(1,LazyList.size(list));
        assertEquals(null,LazyList.get(list,0));

        list="a";
        list=LazyList.add(list, null);
        assertEquals(2,LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));
        assertEquals(null,LazyList.get(list,1));

        list=LazyList.add(list, null);
        assertEquals(3,LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));
        assertEquals(null,LazyList.get(list,1));
        assertEquals(null,LazyList.get(list,2));
    }
