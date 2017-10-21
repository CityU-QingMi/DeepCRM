    @Test
    public void testAddCollection_GenricListInput()
    {
        Collection<?> coll = Arrays.asList("a","b","c");

        List<String> input = new ArrayList<String>();
        input.add("x");
        input.add("y");
        input.add("z");

        Object list = LazyList.addCollection(input,coll);
        assertTrue(list instanceof List);
        assertEquals(6, LazyList.size(list));
        assertEquals("x",LazyList.get(list,0));
        assertEquals("y",LazyList.get(list,1));
        assertEquals("z",LazyList.get(list,2));
        assertEquals("a",LazyList.get(list,3));
        assertEquals("b",LazyList.get(list,4));
        assertEquals("c",LazyList.get(list,5));
    }
