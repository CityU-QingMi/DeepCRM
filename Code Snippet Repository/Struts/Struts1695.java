    public void testPopulateComponentHtmlId5() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        ValueStack stack = ActionContext.getContext().getValueStack();

        Submit submit = new Submit(stack, req, res);
        submit.setName("submitName");

        submit.populateComponentHtmlId(null);

        assertEquals("submitName", submit.getParameters().get("id"));
    }
