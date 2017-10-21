	public void testInvokePrefixMethod2() throws Exception {
		PrefixMethodInvocationUtilTest.Action1 action = new PrefixMethodInvocationUtilTest.Action1();
		
		// ActionProxy
		ActionProxy mockActionProxy = (ActionProxy) createMock(ActionProxy.class);		
		expect(mockActionProxy.getMethod()).andStubReturn("submit");
		
		
		// ActionInvocation
		ActionInvocation mockActionInvocation = (ActionInvocation) createMock(ActionInvocation.class);
		
		expect(mockActionInvocation.getAction()).andStubReturn(action);
		expect(mockActionInvocation.getProxy()).andStubReturn(mockActionProxy);
		
		replay(mockActionProxy, mockActionInvocation);
		
		PrefixMethodInvocationUtil.invokePrefixMethod(
				mockActionInvocation, 
				new String[] { "prepare", "prepareDo" });
		
	
		assertFalse(action.prepareSaveInvoked);
		assertFalse(action.prepareDoSaveInvoked);
		assertTrue(action.prepareSubmitInvoked);
		assertFalse(action.prepareDoCancelInvoked);
		
		verify(mockActionProxy, mockActionInvocation);
	}
