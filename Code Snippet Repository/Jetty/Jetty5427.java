    @Test
    public void testAddObjectObject_NullInput_LazyListItem()
    {
        Object item = LazyList.add(null, "x");
        item = LazyList.add(item,"y");
        item = LazyList.add(item,"z");

        Object list = LazyList.add(null, item);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(1,LazyList.size(list));

        Object val = LazyList.get(list, 0);
        assertTrue(val instanceof List);
    }
