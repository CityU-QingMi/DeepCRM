    @Test
    public void testIterator_LazyListInput()
    {
        Object input = LazyList.add(null,"a");
        input = LazyList.add(input,"b");
        input = LazyList.add(input,"c");

        Iterator<?> iter = LazyList.iterator(input);
        assertNotNull(iter);
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("c", iter.next());
        assertFalse(iter.hasNext());
    }
