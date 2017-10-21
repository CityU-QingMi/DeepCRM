    @Test
    public void testRemoveObjectInt_NonListInput()
    {
        String input = "a";

        // Invalid index
        Object list = LazyList.remove(input, 1);
        assertNotNull(list);
        if(STRICT) {
            assertTrue(list instanceof List);
        }
        assertEquals(1, LazyList.size(list));

        // Valid index
        list = LazyList.remove(input, 0);
        // TODO: should this be null? or an empty list?
        assertNull(list); // nothing left in list
        assertEquals(0, LazyList.size(list));
    }
