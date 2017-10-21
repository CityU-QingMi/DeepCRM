    @Test
    public void testRemoveObjectObject_LazyListInput()
    {
        Object input = LazyList.add(null, "a");
        input = LazyList.add(input, "b");
        input = LazyList.add(input, "c");

        // Remove null item
        Object list = LazyList.remove(input, null);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));

        // Attempt to remove something that doesn't exist
        list = LazyList.remove(input, "z");
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(3, LazyList.size(list));

        // Remove something that exists in input
        list = LazyList.remove(input, "b");
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(2, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
        assertEquals("c", LazyList.get(list, 1));
    }
