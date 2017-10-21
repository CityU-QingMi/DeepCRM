    public void testProxiedActionIsNotAccessible() throws Exception {
        // given
        Map<String, Object> params = new HashMap<>();
        params.put("exposeProxy", "true");
        params.put("issueId", "S2-047");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy(null,
                "chaintoAOPedTestSubBeanAction", null, extraContext);

        // when
        proxy.execute();
        Object action = proxy.getAction();

        //then
        assertEquals("S2-047", ((TestSubBean) action).getIssueId());
        assertFalse("proxied action is accessible!",
                (boolean) MethodUtils.invokeMethod(action, "isExposeProxy"));
    }
