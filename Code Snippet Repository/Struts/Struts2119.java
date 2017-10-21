    public void testMergingIteratorWithArrayAsSource() throws Exception {
        MergeIteratorTag tag = new MergeIteratorTag();
        tag.setPageContext(pageContext);
        tag.setVar("myMergedIterator");

        ParamTag iterator1ParamTag = new ParamTag();
        iterator1ParamTag.setPageContext(pageContext);
        iterator1ParamTag.setValue("myArr1");

        ParamTag iterator2ParamTag = new ParamTag();
        iterator2ParamTag.setPageContext(pageContext);
        iterator2ParamTag.setValue("myArr2");

        ParamTag iterator3ParamTag = new ParamTag();
        iterator3ParamTag.setPageContext(pageContext);
        iterator3ParamTag.setValue("myArr3");


        tag.doStartTag();
        iterator1ParamTag.doStartTag();
        iterator1ParamTag.doEndTag();
        iterator2ParamTag.doStartTag();
        iterator2ParamTag.doEndTag();
        iterator3ParamTag.doStartTag();
        iterator3ParamTag.doEndTag();
        tag.doEndTag();

        Iterator mergedIterator = (Iterator) stack.findValue("#myMergedIterator"); // if not iterator, let CCE surface

        assertNotNull(mergedIterator);
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "1");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "a");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "A");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "2");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "b");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "B");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "3");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "c");
        assertTrue(mergedIterator.hasNext());
        assertEquals(mergedIterator.next(), "C");
        assertFalse(mergedIterator.hasNext());
    }
