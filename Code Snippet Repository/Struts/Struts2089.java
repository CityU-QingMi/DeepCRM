    public void testGeneratorWithId() throws Exception {
        IteratorGeneratorTag tag = new IteratorGeneratorTag();
        tag.setPageContext(pageContext);
        tag.setVal("%{'aaa,bbb,ccc,ddd,eee'}");
        tag.setVar("myPageContextAttId");
        tag.doStartTag();
        tag.doEndTag();

        Object pageContextIterator = stack.findValue("myPageContextAttId");

        assertTrue(pageContextIterator instanceof Iterator);
        // 1
        assertTrue(((Iterator)pageContextIterator).hasNext());
        assertEquals(((Iterator)pageContextIterator).next(), "aaa");
        // 2.
        assertTrue(((Iterator)pageContextIterator).hasNext());
        assertEquals(((Iterator)pageContextIterator).next(), "bbb");
        // 3.
        assertTrue(((Iterator)pageContextIterator).hasNext());
        assertEquals(((Iterator)pageContextIterator).next(), "ccc");
        // 4
        assertTrue(((Iterator)pageContextIterator).hasNext());
        assertEquals(((Iterator)pageContextIterator).next(), "ddd");
        // 5
        assertTrue(((Iterator)pageContextIterator).hasNext());
        assertEquals(((Iterator)pageContextIterator).next(), "eee");

        assertFalse(((Iterator)pageContextIterator).hasNext());
    }
