    @Test
    public void testIterator_NonListInput()
    {
        String input = "a";

        Iterator<?> iter = LazyList.iterator(input);
        assertNotNull(iter);
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertFalse(iter.hasNext());
    }
