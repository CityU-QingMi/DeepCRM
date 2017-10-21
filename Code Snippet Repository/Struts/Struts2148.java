    public void testWithId() throws Exception {
        {   // List as Source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myList");
            tag.setVar("myPageContextId1");

            tag.doStartTag();
            Iterator subsetIterator1 = (Iterator) stack.findValue("top");
            tag.doEndTag();

            Iterator subsetIterator2 = (Iterator) pageContext.getAttribute("myPageContextId1");

            assertNotNull(subsetIterator1);
            assertNotNull(subsetIterator2);
            assertEquals(subsetIterator1, subsetIterator2);
            assertEquals(subsetIterator2.next(), new Integer(1));
            assertEquals(subsetIterator2.next(), new Integer(2));
            assertEquals(subsetIterator2.next(), new Integer(3));
            assertEquals(subsetIterator2.next(), new Integer(4));
            assertEquals(subsetIterator2.next(), new Integer(5));
        }

        {   // Array as source
            SubsetIteratorTag tag = new SubsetIteratorTag();
            tag.setPageContext(pageContext);
            tag.setSource("myArray");
            tag.setVar("myPageContextId2");

            tag.doStartTag();
            Iterator subsetIterator1 = (Iterator) stack.findValue("top");
            tag.doEndTag();

            Iterator subsetIterator2 = (Iterator) pageContext.getAttribute("myPageContextId2");

            assertNotNull(subsetIterator1);
            assertNotNull(subsetIterator2);
            assertEquals(subsetIterator1, subsetIterator2);
            assertEquals(subsetIterator2.next(), new Integer(1));
            assertEquals(subsetIterator2.next(), new Integer(2));
            assertEquals(subsetIterator2.next(), new Integer(3));
            assertEquals(subsetIterator2.next(), new Integer(4));
            assertEquals(subsetIterator2.next(), new Integer(5));
        }
    }
