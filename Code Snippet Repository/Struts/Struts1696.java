    public void testPopulateComponentHtmlId6() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        ValueStack stack = ActionContext.getContext().getValueStack();

        Submit submit = new Submit(stack, req, res);
        submit.setAction("submitAction");
        submit.setMethod("submitMethod");

        submit.populateComponentHtmlId(null);

        assertEquals("submitAction_submitMethod", submit.getParameters().get("id"));
    }
