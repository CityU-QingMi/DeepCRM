    @Test
    public void testClone_GenericListInput()
    {
        List<String> input = new ArrayList<String>();
        input.add("a");
        input.add("b");
        input.add("c");

        // TODO: decorate the .clone(Object) method to return
        //       the same generic object element type
        Object list = LazyList.clone(input);
        assertNotNull(list);
        assertTrue("Should be a List object", list instanceof List);
        assertFalse("Should NOT be the same object", input == list);
        assertEquals(3, LazyList.size(list));
        assertEquals("a", LazyList.get(list,0));
        assertEquals("b", LazyList.get(list,1));
        assertEquals("c", LazyList.get(list,2));
    }
