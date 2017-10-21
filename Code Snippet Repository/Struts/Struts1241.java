	@Override
    protected void setUp() throws Exception {
		contextMap = new LinkedHashMap<>();
		context = new ActionContext(contextMap);
		
		actionInvocation = (ActionInvocation) createMock(ActionInvocation.class);
		expect(actionInvocation.getAction()).andStubReturn(new SampleAction());
		expect(actionInvocation.getInvocationContext()).andStubReturn(context);
		expect(actionInvocation.invoke()).andStubReturn("success");
	}
