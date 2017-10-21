    @Test
    public void testAddObjectIntObject_NonListInput_NullItem()
    {
        String input = "a";

        Object list = LazyList.add(input, 0, null);
        assertNotNull(list);
        assertEquals(2,LazyList.size(list));
        assertEquals(null, LazyList.get(list,0));
        assertEquals("a", LazyList.get(list,1));
    }
