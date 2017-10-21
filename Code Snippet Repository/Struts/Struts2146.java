    public void testWithCountAttribute() throws Exception {
        { // List as source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myList");
            tag.setCount("3");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(1));
            assertEquals(subsetIterator.next(), new Integer(2));
            assertEquals(subsetIterator.next(), new Integer(3));
        }

        { // array as source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myArray");
            tag.setCount("3");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(1));
            assertEquals(subsetIterator.next(), new Integer(2));
            assertEquals(subsetIterator.next(), new Integer(3));
        }
    }
