    @Test
    public void testListIterator_NonListInput()
    {
        String input = "a";

        ListIterator<?> iter = LazyList.listIterator(input);
        assertNotNull(iter);
        assertTrue(iter.hasNext());
        assertFalse(iter.hasPrevious());
        assertEquals("a", iter.next());
        assertFalse(iter.hasNext());
        assertTrue(iter.hasPrevious());
    }
