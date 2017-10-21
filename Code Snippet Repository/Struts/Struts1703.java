    public void testPopulateComponentHtmlIdWithOgnl() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        Form form = new Form(stack, req, res);
        form.getParameters().put("id", "formId");

        TextField txtFld = new TextField(stack, req, res);
        txtFld.setName("txtFldName%{'1'}");

        txtFld.populateComponentHtmlId(form);

        assertEquals("formId_txtFldName1", txtFld.getParameters().get("id"));
    }
