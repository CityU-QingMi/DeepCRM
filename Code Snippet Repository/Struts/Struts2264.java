    public void testFormTagForStackOverflowException3() throws Exception {
        request.setRequestURI("/requestUri");

        FormTag form1 = new FormTag();
        form1.setPageContext(pageContext);
        form1.doStartTag();

        assertEquals(form1.getComponent().getComponentStack().size(), 1);

        FormTag form2 = new FormTag();
        form2.setPageContext(pageContext);
        form2.doStartTag();

        assertEquals(form2.getComponent().getComponentStack().size(), 2);

        FormTag form3 = new FormTag();
        form3.setPageContext(pageContext);
        form3.doStartTag();

        assertEquals(form3.getComponent().getComponentStack().size(), 3);

        ActionTag tag = new ActionTag();
        tag.setPageContext(pageContext);
        tag.setName("testAction");
        tag.doStartTag();

        assertEquals(tag.getComponent().getComponentStack().size(), 4);

        tag.doEndTag();

        assertEquals(form3.getComponent().getComponentStack().size(), 3);

        form3.doEndTag();

        assertEquals(form2.getComponent().getComponentStack().size(), 2);

        form2.doEndTag();

        assertEquals(form1.getComponent().getComponentStack().size(), 1);

        form1.doEndTag();

        assertNull(form1.getComponent()); // component is removed after end tag
    }
