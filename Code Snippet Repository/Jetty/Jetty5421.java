    @Test
    public void testAddCollection_NullInput()
    {
        Collection<?> coll = Arrays.asList("a","b","c");

        Object list = LazyList.addCollection(null,coll);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));
        assertEquals("b",LazyList.get(list,1));
        assertEquals("c",LazyList.get(list,2));
    }
