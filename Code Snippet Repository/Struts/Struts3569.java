	public void testRestoreValueStackInRenderPhaseWhenNotProperPrg() throws Exception {
		RenderRequest renderRequest = EasyMock.createNiceMock(RenderRequest.class);
		ActionInvocation invocation = EasyMock.createNiceMock(ActionInvocation.class);
		
		ValueStack eventPhaseStack = container.getInstance(ValueStackFactory.class).createValueStack();
		eventPhaseStack.set("testKey", "testValue");
		
		ValueStack currentStack = container.getInstance(ValueStackFactory.class).createValueStack();
		currentStack.set("anotherTestKey", "anotherTestValue");
		
		EasyMock.expect(invocation.getStack()).andStubReturn(currentStack);
		
		Map<String, Object> ctxMap = new HashMap<String, Object>();
		Map<String, Object> session = new HashMap<String, Object>();
		
		session.put(STACK_FROM_EVENT_PHASE, eventPhaseStack);
		
		ctxMap.put(PHASE, PortletPhase.RENDER_PHASE);
		ctxMap.put(REQUEST, renderRequest);
		
		ActionContext ctx = new ActionContext(ctxMap);
		ctx.setSession(session);
		
		EasyMock.expect(invocation.getInvocationContext()).andStubReturn(ctx);
		EasyMock.expect(invocation.getStack()).andStubReturn(currentStack);
		EasyMock.expect(invocation.getAction()).andStubReturn(new DirectRenderFromEventAction());
		EasyMock.expect(renderRequest.getParameter(EVENT_ACTION)).andStubReturn("true");
		
		EasyMock.replay(renderRequest);
		EasyMock.replay(invocation);
		
		interceptor.intercept(invocation);
		
		ValueStack resultingStack = invocation.getStack();
		assertEquals("testValue", resultingStack.findValue("testKey"));
		assertEquals("anotherTestValue", resultingStack.findValue("anotherTestKey"));
		
		
	}
