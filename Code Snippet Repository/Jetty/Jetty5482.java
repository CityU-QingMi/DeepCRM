    @Test
    public void testAddObjectIntObject_NullInput_NonListItem()
    {
        String item = "a";
        Object list = LazyList.add(null, 0, item);
        assertNotNull(list);
        if(STRICT) {
            assertTrue(list instanceof List);
        }
        assertEquals(1,LazyList.size(list));
    }
