    @Test
    public void testClone_LazyListInput()
    {
        Object input = LazyList.add(null,"a");
        input = LazyList.add(input,"b");
        input = LazyList.add(input,"c");

        Object list = LazyList.clone(input);
        assertNotNull(list);
        assertTrue("Should be a List object", list instanceof List);
        assertFalse("Should NOT be the same object", input == list);
        assertEquals(3, LazyList.size(list));
        assertEquals("a", LazyList.get(list,0));
        assertEquals("b", LazyList.get(list,1));
        assertEquals("c", LazyList.get(list,2));
    }
