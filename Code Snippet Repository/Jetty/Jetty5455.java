    @Test
    public void testRemoveObjectObject_LinkedListInput()
    {
        // Should be able to use any collection object.
        List<String> input = new LinkedList<String>();
        input.add("a");
        input.add("b");
        input.add("c");

        // Remove null item
        Object list = LazyList.remove(input, null);
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertTrue("Should not have recreated list obj", input == list);
        assertEquals(3, LazyList.size(list));

        // Attempt to remove something that doesn't exist
        list = LazyList.remove(input, "z");
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertTrue("Should not have recreated list obj", input == list);
        assertEquals(3, LazyList.size(list));

        // Remove something that exists in input
        list = LazyList.remove(input, "b");
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertTrue("Should not have recreated list obj", input == list);
        assertEquals(2, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
        assertEquals("c", LazyList.get(list, 1));
    }
