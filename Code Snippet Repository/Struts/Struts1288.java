    public void testPrefixInvocation2() throws Exception {

    	
    	ActionProxy mockActionProxy = (ActionProxy) createMock(ActionProxy.class);   	
    	
    	expect(mockActionProxy.getMethod()).andStubReturn("save");
    	
    	
    	ActionInvocation mockActionInvocation = (ActionInvocation) createMock(ActionInvocation.class);
    	
    	expect(mockActionInvocation.getAction()).andStubReturn(mockAction);
    	expect(mockActionInvocation.invoke()).andStubReturn("okok");
    	expect(mockActionInvocation.getProxy()).andStubReturn(mockActionProxy);       
        
    	mockAction.prepare();
        expectLastCall().times(1);  	
    	
        replay(mockAction, mockActionProxy, mockActionInvocation);
    	
    	PrepareInterceptor interceptor = new PrepareInterceptor();
    	String result = interceptor.intercept(mockActionInvocation);
    	
    	assertEquals("okok", result);
    	
    	verify(mockAction, mockActionProxy, mockActionInvocation);
    }
