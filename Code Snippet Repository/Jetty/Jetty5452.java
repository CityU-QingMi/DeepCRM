    @Test
    public void testRemoveObjectObject_NonListInput()
    {
        String input = "a";

        // Remove null item
        Object list = LazyList.remove(input, null);
        assertNotNull(list);
        if(STRICT) {
            assertTrue(list instanceof List);
        }
        assertEquals(1, LazyList.size(list));

        // Remove item that doesn't exist
        list = LazyList.remove(input, "b");
        assertNotNull(list);
        if(STRICT) {
            assertTrue(list instanceof List);
        }
        assertEquals(1, LazyList.size(list));

        // Remove item that exists
        list = LazyList.remove(input, "a");
        // TODO: should this be null? or an empty list?
        assertNull(list); // nothing left in list
        assertEquals(0, LazyList.size(list));
    }
