    @Test
    public void testAddCollection_GenericListInput()
    {
        List<String> l=new ArrayList<String>();
        l.add("a");
        l.add("b");

        Object list=null;
        list=LazyList.addCollection(list,l);
        list=LazyList.addCollection(list,l);

        assertEquals(4,LazyList.size(list));
        assertEquals("a",LazyList.get(list,0));
        assertEquals("b",LazyList.get(list,1));
        assertEquals("a",LazyList.get(list,2));
        assertEquals("b",LazyList.get(list,3));
    }
