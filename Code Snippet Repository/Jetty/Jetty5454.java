    @Test
    public void testRemoveObjectObject_GenericListInput()
    {
        List<String> input = new ArrayList<String>();
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

        // Try to remove the rest.
        list = LazyList.remove(list,"a");
        list = LazyList.remove(list,"c");
        assertNull(list);
    }
