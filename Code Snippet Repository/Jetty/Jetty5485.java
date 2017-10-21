    @Test
    public void testListIterator_LazyListInput()
    {
        Object input = LazyList.add(null,"a");
        input = LazyList.add(input,"b");
        input = LazyList.add(input,"c");

        ListIterator<?> iter = LazyList.listIterator(input);
        assertNotNull(iter);
        assertTrue(iter.hasNext());
        assertFalse(iter.hasPrevious());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("c", iter.next());
        assertFalse(iter.hasNext());
        assertTrue(iter.hasPrevious());
        assertEquals("c", iter.previous());
        assertEquals("b", iter.previous());
        assertEquals("a", iter.previous());
        assertFalse(iter.hasPrevious());
    }
