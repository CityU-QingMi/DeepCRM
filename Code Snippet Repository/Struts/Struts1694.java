    public void testPopulateComponentHtmlId4() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        ValueStack stack = ActionContext.getContext().getValueStack();

        Submit submit = new Submit(stack, req, res);
        submit.setId("submitId");

        submit.populateComponentHtmlId(null);

        assertEquals("submitId", submit.getParameters().get("id"));
    }
