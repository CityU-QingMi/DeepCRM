    public void testSortWithoutId() throws Exception {
        SortIteratorTag tag = new SortIteratorTag();

        tag.setComparator("comparator");
        tag.setSource("source");

        tag.setPageContext(pageContext);
        tag.doStartTag();

        // if not an Iterator, just let the ClassCastException be thrown as error instead of failure
        Iterator sortedIterator = (Iterator) stack.findValue("top");

        assertNotNull(sortedIterator);
        // 1
        assertTrue(sortedIterator.hasNext());
        assertEquals(sortedIterator.next(), new Integer(1));
        // 2
        assertTrue(sortedIterator.hasNext());
        assertEquals(sortedIterator.next(), new Integer(2));
        // 3.
        assertTrue(sortedIterator.hasNext());
        assertEquals(sortedIterator.next(), new Integer(3));
        // 4.
        assertTrue(sortedIterator.hasNext());
        assertEquals(sortedIterator.next(), new Integer(4));
        // 5
        assertTrue(sortedIterator.hasNext());
        assertEquals(sortedIterator.next(), new Integer(5));

        assertFalse(sortedIterator.hasNext());
        tag.doEndTag();
    }
