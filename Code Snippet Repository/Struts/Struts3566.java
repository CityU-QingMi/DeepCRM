	public void testPortletRequestIsSet() throws Exception {
		PortletRequest request = EasyMock.createMock(PortletRequest.class);
		Map<String, Object> ctx = new HashMap<String, Object>();
		ctx.put(PortletConstants.REQUEST, request);
		PortletRequestAware action = EasyMock.createMock(PortletRequestAware.class);
		action.setPortletRequest(request);
		
		ActionInvocation invocation = EasyMock.createNiceMock(ActionInvocation.class);
		EasyMock.expect(invocation.getInvocationContext()).andReturn(new ActionContext(ctx));
		EasyMock.expect(invocation.getAction()).andReturn(action);
		
		EasyMock.replay(action);
		EasyMock.replay(invocation);
		
		interceptor.intercept(invocation);
		
		EasyMock.verify(action);
	}
