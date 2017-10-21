    public void testGeneratorWithConverter() throws Exception {
        IteratorGeneratorTag tag = new IteratorGeneratorTag();

        tag.setPageContext(pageContext);
        tag.setVal("%{'aaa, bbb, ccc, ddd, eee'}");
        tag.setConverter("myConverter");
        tag.doStartTag();
        Object topOfStack = stack.findValue("top");
        tag.doEndTag();
        Object afterTopOfStack = stack.findValue("top");

        assertTrue(topOfStack instanceof Iterator);
        // 1.
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "myConverter-aaa");
        // 2
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "myConverter-bbb");
        // 3
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "myConverter-ccc");
        // 4.
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "myConverter-ddd");
        // 5.
        assertTrue(((Iterator)topOfStack).hasNext());
        assertEquals(((Iterator)topOfStack).next(), "myConverter-eee");

        assertFalse(((Iterator)topOfStack).hasNext());
        assertNotSame(afterTopOfStack, topOfStack);
    }
