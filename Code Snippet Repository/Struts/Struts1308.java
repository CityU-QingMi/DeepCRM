	public void testBlockingByDefault() throws Exception {

		Map<String, Object> contextMap = new HashMap<>();
		Map<String, Object> parameterMap = new HashMap<>();
		
		parameterMap.put("job", "Baker");
		parameterMap.put("name", "Martin");

		contextMap.put(ActionContext.PARAMETERS, HttpParameters.create(parameterMap).build());
		
		Action action = new BlockingByDefaultAction();
		stack.push(action);
		
		Mock mockInvocation = new Mock(ActionInvocation.class);
		mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));
		mockInvocation.matchAndReturn("getAction", action);
		mockInvocation.matchAndReturn("getStack", stack);
		mockInvocation.expectAndReturn("invoke", Action.SUCCESS);
		mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));
		mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));

		ActionInvocation invocation = (ActionInvocation) mockInvocation.proxy();
		
		AnnotationParameterFilterInterceptor interceptor = new AnnotationParameterFilterInterceptor();
		interceptor.intercept(invocation);

		HttpParameters parameters = invocation.getInvocationContext().getParameters();
		assertEquals("Parameter map should contain one entry", 1, parameters.keySet().size());
		assertFalse(parameters.get("job").isDefined());
		assertTrue(parameters.get("name").isDefined());
		
	}
