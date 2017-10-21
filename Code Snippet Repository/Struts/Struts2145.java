    public void testWithStartAttribute() throws Exception {
        { // List as source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myList");
            tag.setStart("3");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(4));
            assertEquals(subsetIterator.next(), new Integer(5));
        }

        { // Array as source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myArray");
            tag.setStart("3");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(4));
            assertEquals(subsetIterator.next(), new Integer(5));
        }
    }
