    public void testAppendingIteratorUsingArrayAsSource() throws Exception {
        AppendIteratorTag tag = new AppendIteratorTag();
        tag.setPageContext(pageContext);
        tag.setVar("myAppendedIterator");

        ParamTag iterator1ParamTag = new ParamTag();
        iterator1ParamTag.setPageContext(pageContext);
        iterator1ParamTag.setValue("%{myArr1}");

        ParamTag iterator2ParamTag = new ParamTag();
        iterator2ParamTag.setPageContext(pageContext);
        iterator2ParamTag.setValue("%{myArr2}");

        ParamTag iterator3ParamTag = new ParamTag();
        iterator3ParamTag.setPageContext(pageContext);
        iterator3ParamTag.setValue("%{myArr3}");


        tag.doStartTag();
        iterator1ParamTag.doStartTag();
        iterator1ParamTag.doEndTag();
        iterator2ParamTag.doStartTag();
        iterator2ParamTag.doEndTag();
        iterator3ParamTag.doStartTag();
        iterator3ParamTag.doEndTag();
        tag.doEndTag();

        Iterator appendedIterator = (Iterator) stack.findValue("#myAppendedIterator");

        assertNotNull(appendedIterator);
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "1");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "2");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "3");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "a");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "b");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "c");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "A");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "B");
        assertTrue(appendedIterator.hasNext());
        assertEquals(appendedIterator.next(), "C");
        assertFalse(appendedIterator.hasNext());
    }
