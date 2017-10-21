    @Test
    public void testAddObjectIntObject_NullInput_GenericListItem()
    {
        List<String> item = new ArrayList<String>();
        item.add("a");

        Object list = LazyList.add(null, 0, item);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(1,LazyList.size(list));
    }
