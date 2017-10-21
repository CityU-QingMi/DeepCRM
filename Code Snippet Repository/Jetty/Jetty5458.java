    @Test
    public void testRemoveObjectInt_GenericListInput()
    {
        List<String> input = new ArrayList<String>();
        input.add("a");
        input.add("b");
        input.add("c");

        Object list = null;

        if (STRICT)
        {
            // Invalid index
            // Shouldn't cause a IndexOutOfBoundsException as this is not the
            // same behavior you experience in testRemoveObjectInt_NonListInput and
            // testRemoveObjectInt_NullInput when using invalid indexes.
            list = LazyList.remove(input,5);
            assertNotNull(list);
            assertTrue(list instanceof List);
            assertEquals(3, LazyList.size(list));
        }

        // Valid index
        list = LazyList.remove(input, 1); // remove the 'b'
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(2, LazyList.size(list));
        assertEquals("a", LazyList.get(list, 0));
        assertEquals("c", LazyList.get(list, 1));

        // Remove the rest
        list = LazyList.remove(list, 0); // the 'a'
        list = LazyList.remove(list, 0); // the 'c'
        assertNull(list);
    }
