    @Test
    public void testAddCollection_Sequential()
    {
        Collection<?> coll = Arrays.asList("a","b");

        Object list = null;
        list = LazyList.addCollection(list,coll);
        list = LazyList.addCollection(list,coll);

        assertEquals(4,LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));
        assertEquals("b",LazyList.get(list,1));
        assertEquals("a",LazyList.get(list,2));
        assertEquals("b",LazyList.get(list,3));
    }
