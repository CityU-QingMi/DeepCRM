    public void testPopulateComponentHtmlId1() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        Form form = new Form(stack, req, res);
        form.getParameters().put("id", "formId");

        TextField txtFld = new TextField(stack, req, res);
        txtFld.setId("txtFldId");

        txtFld.populateComponentHtmlId(form);

        assertEquals("txtFldId", txtFld.getParameters().get("id"));
    }
