    @Test
    public void testAddObjectObject_NullInput_NonListItem()
    {
        String item = "a";
        Object list = LazyList.add(null, item);
        assertNotNull(list);
        if(STRICT) {
            assertTrue(list instanceof List);
        }
        assertEquals(1,LazyList.size(list));
    }
