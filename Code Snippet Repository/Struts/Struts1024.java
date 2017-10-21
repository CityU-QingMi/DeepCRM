    public void testNamespaceAndActionExpressionEvaluation() throws Exception {
        ActionChainResult result = new ActionChainResult();
        result.setActionName("${actionName}");
        result.setNamespace("${namespace}");

        String expectedActionName = "testActionName";
        String expectedNamespace = "testNamespace";
        Map<String, Object> values = new HashMap<>();
        values.put("actionName", expectedActionName);
        values.put("namespace", expectedNamespace);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(values);

        Mock actionProxyMock = new Mock(ActionProxy.class);
        actionProxyMock.expect("execute");

        ActionProxyFactory testActionProxyFactory = new NamespaceActionNameTestActionProxyFactory(expectedNamespace, expectedActionName, (ActionProxy) actionProxyMock.proxy());
        result.setActionProxyFactory(testActionProxyFactory);
        try {

            ActionContext testContext = new ActionContext(stack.getContext());
            ActionContext.setContext(testContext);
            result.execute(null);
            actionProxyMock.verify();
        } finally {
            ActionContext.setContext(null);
        }
    }
