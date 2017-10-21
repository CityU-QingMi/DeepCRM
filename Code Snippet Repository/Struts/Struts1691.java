    public void testPopulateComponentHtmlId1() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        ValueStack stack = ActionContext.getContext().getValueStack();

        Form form = new Form(stack, req, res);
        form.getParameters().put("id", "formId");

        Submit submit = new Submit(stack, req, res);
        submit.setId("submitId");

        submit.populateComponentHtmlId(form);

        assertEquals("submitId", submit.getParameters().get("id"));
    }
