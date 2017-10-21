    public void testPopulateComponentHtmlId2() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        ValueStack stack = ActionContext.getContext().getValueStack();

        Form form = new Form(stack, req, res);
        form.getParameters().put("id", "formId");

        Submit submit = new Submit(stack, req, res);
        submit.setName("submitName");

        submit.populateComponentHtmlId(form);

        assertEquals("formId_submitName", submit.getParameters().get("id"));
    }
