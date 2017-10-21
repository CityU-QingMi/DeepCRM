    public void testGeneratorBasic() throws Exception {
        IteratorGeneratorTag tag = new IteratorGeneratorTag();

        tag.setPageContext(pageContext);
        tag.setVal("%{'aaa,bbb,ccc,ddd,eee'}");
        tag.doStartTag();
        Object topOfStack = stack.findValue("top");


        assertTrue(topOfStack instanceof Iterator);
        // 1
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "aaa");
        // 2
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "bbb");
        // 3
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "ccc");
        // 4
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "ddd");
        // 5
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(),"eee");

        assertFalse(((Iterator)topOfStack).hasNext());

        tag.doEndTag();
        Object afterTopOfStack = stack.findValue("top");


        assertNotSame(afterTopOfStack, topOfStack);
    }
