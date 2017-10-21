    public void testSimple() {
        PushTag tag = new PushTag();

        stack.setValue("foo", "bar");

        tag.setPageContext(pageContext);
        tag.setValue("foo");

        try {
            assertEquals(2, stack.size());
            tag.doStartTag();
            assertEquals(3, stack.size());
            tag.doEndTag();
            assertEquals(2, stack.size());
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }
    }
