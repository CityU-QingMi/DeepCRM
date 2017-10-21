    @Test
    public void testRemoveObjectInt_LazyListInput()
    {
        Object input = LazyList.add(null, "a");
        input = LazyList.add(input, "b");
        input = LazyList.add(input, "c");

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
    }
