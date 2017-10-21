    public void testWithDecider() throws Exception {
        {   // List as source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myList");
            tag.setDecider("myDecider");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(2));
            assertEquals(subsetIterator.next(), new Integer(4));
        }

        {   // Array As source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myList");
            tag.setDecider("myDecider");

            tag.doStartTag();
            Iterator subsetIterator = (Iterator) stack.findValue("top");
            tag.doEndTag();

            assertEquals(subsetIterator.next(), new Integer(2));
            assertEquals(subsetIterator.next(), new Integer(4));
        }
    }
