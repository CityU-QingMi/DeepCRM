	private void setupActionFactory(String namespace, String actionName,
			String result, ValueStack stack) {
		if (mockActionFactory == null) {
			mockActionFactory = mock(ActionProxyFactory.class);
		}
		mockAction = mock(Action.class);
		mockActionProxy = mock(ActionProxy.class);
		mockInvocation = mock(ActionInvocation.class);

		mockActionFactory
				.expects(once())
				.method("createActionProxy")
				.with(new Constraint[] { eq(namespace), eq(actionName), NULL,
						isA(Map.class) })
				.will(returnValue(mockActionProxy.proxy()));
		mockActionProxy.stubs().method("getAction")
				.will(returnValue(mockAction.proxy()));
		mockActionProxy.expects(once()).method("execute")
				.will(returnValue(result));
		mockActionProxy.expects(once()).method("getInvocation")
				.will(returnValue(mockInvocation.proxy()));
		mockInvocation.stubs().method("getStack").will(returnValue(stack));

	}
