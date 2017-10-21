    public void testPopulateComponentHtmlId3() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        ValueStack stack = ActionContext.getContext().getValueStack();

        Form form = new Form(stack, req, res);
        form.getParameters().put("id", "formId");

        Submit submit = new Submit(stack, req, res);
        submit.setAction("submitAction");
        submit.setMethod("submitMethod");

        submit.populateComponentHtmlId(form);

        assertEquals("formId_submitAction_submitMethod", submit.getParameters().get("id"));
    }
