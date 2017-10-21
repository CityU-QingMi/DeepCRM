	public void testAllowingByDefaultWithModel() throws Exception {

		Map<String, Object> contextMap = new HashMap<>();
		Map<String, Object> parameterMap = new HashMap<>();
		
		parameterMap.put("job", "Baker");
		parameterMap.put("name", "Martin");
		parameterMap.put("m1", "s1");
		parameterMap.put("m2", "s2");

		contextMap.put(ActionContext.PARAMETERS, HttpParameters.create(parameterMap).build());
		stack.push(new AllowingByDefaultModel());
		
		Mock mockInvocation = new Mock(ActionInvocation.class);
		mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));
		mockInvocation.matchAndReturn("getAction", new AllowingByDefaultAction());
		mockInvocation.matchAndReturn("getStack", stack);
		mockInvocation.expectAndReturn("invoke", Action.SUCCESS);
		mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));
		mockInvocation.expectAndReturn("getInvocationContext", new ActionContext(contextMap));

		ActionInvocation invocation = (ActionInvocation) mockInvocation.proxy();
		
		AnnotationParameterFilterInterceptor interceptor = new AnnotationParameterFilterInterceptor();
		interceptor.intercept(invocation);

		HttpParameters parameters = invocation.getInvocationContext().getParameters();
		assertEquals("Parameter map should contain two entries", 2, parameters.keySet().size());
		assertTrue(parameters.get("job").isDefined());
		assertFalse(parameters.get("name").isDefined());
		assertFalse(parameters.get("m1").isDefined());
		assertTrue(parameters.get("m2").isDefined());
		
	}
