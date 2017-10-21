    public void testSortWithIllegalSource() throws Exception {
        SortIteratorTag tag = new SortIteratorTag();

        tag.setComparator("comparator");
        tag.setSource("badSource");

        try {
            tag.setPageContext(pageContext);
            tag.doStartTag();
            tag.doEndTag();
            fail("JspException expected");
        }
        catch (JspException e) {
            // ok
            assertTrue(true);
        }
    }
