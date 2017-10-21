    public void testValidateOnlyFalse() throws Exception {
        JSONActionRedirectResult result = new JSONActionRedirectResult();
        result.setActionName("targetAction");
        result.setActionMapper(new DefaultActionMapper());
        result.setUrlHelper(new DefaultUrlHelper());

        request.setParameter("struts.enableJSONValidation", "true");
        request.setParameter("struts.validateOnly", "true");

        Object action = new Object();
        stack.push(action);

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String content = response.getContentAsString();
        assertEquals("", content);
        String location = response.getHeader("Location");
        assertEquals("/targetAction.action", location);
        assertEquals(302, response.getStatus());
    }
