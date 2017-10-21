    public void testGeneratorWithCount() throws Exception {
        IteratorGeneratorTag tag = new IteratorGeneratorTag();

        tag.setPageContext(pageContext);
        tag.setVal("%{'aaa,bbb,ccc,ddd,eee'}");
        tag.setCount("myCount");
        tag.doStartTag();
        Object topOfStack = stack.findValue("top");
        tag.doEndTag();
        Object afterTopOfStack = stack.findValue("top");


        assertTrue(topOfStack instanceof Iterator);
        // 1
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "aaa");
        // 2
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "bbb");
        // 3.
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "ccc");

        assertFalse(((Iterator)topOfStack).hasNext());
        assertNotSame(topOfStack, afterTopOfStack);
    }
