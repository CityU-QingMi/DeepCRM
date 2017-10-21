    public void testFormComponentIsRemoved() throws Exception {
        request.setRequestURI("/requestUri");

        FormTag form = new FormTag();
        form.setPageContext(pageContext);
        form.doStartTag();

        assertEquals(form.getComponent().getComponentStack().size(), 1);

        form.doEndTag();

        assertNull(form.getComponent());
    }
