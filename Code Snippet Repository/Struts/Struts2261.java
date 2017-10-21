    public void testFormTagForStackOverflowException1() throws Exception {
        request.setRequestURI("/testAction");

        FormTag form1 = new FormTag();
        form1.setPageContext(pageContext);
        form1.doStartTag();

        assertEquals(form1.getComponent().getComponentStack().size(), 1);

        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setName("testAction");
        tag.doStartTag();

        assertEquals(tag.getComponent().getComponentStack().size(), 2);

        tag.doEndTag();

        assertEquals(form1.getComponent().getComponentStack().size(), 1);

        form1.doEndTag();

        assertNull(form1.getComponent()); // component is removed after end tag
    }
