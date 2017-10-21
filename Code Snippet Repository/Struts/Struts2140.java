    public void testSortWithIdIteratorAvailableInPageContext() throws Exception {
        SortIteratorTag tag = new SortIteratorTag();

        tag.setVar("myId");
        tag.setComparator("comparator");
        tag.setSource("source");

        tag.setPageContext(pageContext);
        tag.doStartTag();

        {
            Iterator sortedIterator = (Iterator) pageContext.getAttribute("myId");

            assertNotNull(sortedIterator);
            // 1
            assertTrue(sortedIterator.hasNext());
            assertEquals(sortedIterator.next(), new Integer(1));
            // 2
            assertTrue(sortedIterator.hasNext());
            assertEquals(sortedIterator.next(), new Integer(2));
            // 3
            assertTrue(sortedIterator.hasNext());
            assertEquals(sortedIterator.next(), new Integer(3));
            // 4
            assertTrue(sortedIterator.hasNext());
            assertEquals(sortedIterator.next(), new Integer(4));
            // 5
            assertTrue(sortedIterator.hasNext());
            assertEquals(sortedIterator.next(), new Integer(5));

            assertFalse(sortedIterator.hasNext());
        }

        tag.doEndTag();
    }
