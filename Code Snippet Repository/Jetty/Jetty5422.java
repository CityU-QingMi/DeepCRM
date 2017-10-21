    @Test
    public void testAddCollection_NonListInput()
    {
        Collection<?> coll = Arrays.asList("a","b","c");
        String input = "z";

        Object list = LazyList.addCollection(input,coll);
        assertTrue(list instanceof List);
        assertEquals(4, LazyList.size(list));
        assertEquals("z",LazyList.get(list,0));
        assertEquals("a",LazyList.get(list,1));
        assertEquals("b",LazyList.get(list,2));
        assertEquals("c",LazyList.get(list,3));
    }
