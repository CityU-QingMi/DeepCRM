    public void testSortWithIllegalComparator() throws Exception {
        SortIteratorTag tag = new SortIteratorTag();

        tag.setComparator("badComparator");
        tag.setSource("source");

        try {
            tag.setPageContext(pageContext);
            tag.doStartTag();
            tag.doEndTag();
            fail("JspException expected");
        }
        catch (JspException e) {
            // good
            assertTrue(true);
        }

    }
