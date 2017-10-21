    public void testJsonRedirect() throws Exception {
        JSONActionRedirectResult result = new JSONActionRedirectResult();
        result.setActionName("targetAction");
        result.setActionMapper(new DefaultActionMapper());
        result.setUrlHelper(new DefaultUrlHelper());

        request.setParameter("struts.enableJSONValidation", "true");
        request.setParameter("struts.validateOnly", "false");

        Object action = new Object();
        stack.push(action);

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String content = response.getContentAsString();
        assertEquals("{\"location\": \"/targetAction.action\"}", content);
        assertEquals(200, response.getStatus());
    }
