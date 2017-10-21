    public void testNormalRedirect() throws Exception {
        JSONActionRedirectResult result = new JSONActionRedirectResult();
        result.setActionName("targetAction");
        result.setActionMapper(new DefaultActionMapper());
        result.setUrlHelper(new DefaultUrlHelper());

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
