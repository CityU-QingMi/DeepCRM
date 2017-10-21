	public void testCopyValueStackFromEventToRenderPhase() throws Exception {
		ActionResponse actionResponse = EasyMock.createNiceMock(ActionResponse.class);
		ActionInvocation invocation = EasyMock.createNiceMock(ActionInvocation.class);
		
		Map<String, Object> ctxMap = new HashMap<String, Object>();
		ctxMap.put(PHASE, PortletPhase.ACTION_PHASE);
		ctxMap.put(RESPONSE, actionResponse);
		Map<String, Object> session = new HashMap<String, Object>();
		
		ActionContext ctx = new ActionContext(ctxMap);
		ctx.setSession(session);
		EasyMock.expect(invocation.getInvocationContext()).andStubReturn(ctx);
		actionResponse.setRenderParameter(EVENT_ACTION, "true");
		
		ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();
		EasyMock.expect(invocation.getStack()).andStubReturn(stack);
		
		EasyMock.replay(actionResponse);
		EasyMock.replay(invocation);
		
		interceptor.intercept(invocation);
		
		EasyMock.verify(actionResponse);
		EasyMock.verify(invocation);
		
		assertSame(stack, session.get(STACK_FROM_EVENT_PHASE));
		
	}
