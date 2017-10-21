    public void testBasic() throws Exception {
        { // List as Source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myList");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(1));
            assertEquals(subsetIterator.next(), new Integer(2));
            assertEquals(subsetIterator.next(), new Integer(3));
            assertEquals(subsetIterator.next(), new Integer(4));
            assertEquals(subsetIterator.next(), new Integer(5));
        }

        { // Array as Source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myArray");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(1));
            assertEquals(subsetIterator.next(), new Integer(2));
            assertEquals(subsetIterator.next(), new Integer(3));
            assertEquals(subsetIterator.next(), new Integer(4));
            assertEquals(subsetIterator.next(), new Integer(5));
        }
    }
