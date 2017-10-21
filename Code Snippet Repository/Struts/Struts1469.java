	public void testActionGetTextWithNullObject() throws Exception {
		MyAction action = new MyAction();
        container.inject(action);
		
		Mock mockActionInvocation = new Mock(ActionInvocation.class);
        mockActionInvocation.expectAndReturn("getAction", action);
        ActionContext.getContext().setActionInvocation((ActionInvocation) mockActionInvocation.proxy());
		ActionContext.getContext().getValueStack().push(action);
		
		String message = action.getText("barObj.title");
		assertEquals("Title:", message);
	}
