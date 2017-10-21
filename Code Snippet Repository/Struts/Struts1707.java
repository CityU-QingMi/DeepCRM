    public void testEscapeId() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        Form form = new Form(stack, req, res);
        form.getParameters().put("id", "formId");

        TextField txtFld = new TextField(stack, req, res);
        txtFld.setName("foo/bar");
        txtFld.populateComponentHtmlId(form);
        assertEquals("formId_foo_bar", txtFld.getParameters().get("id"));
    }
