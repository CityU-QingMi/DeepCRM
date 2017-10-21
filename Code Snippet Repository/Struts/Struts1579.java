	public void testGetterSetterGetsCalledApropriately2() throws Exception {
		
		validator1.setRepopulateField(false);
		validator1.validate(action);

		
		ActionContext.getContext().getActionInvocation().invoke();
		
		Object valueFromStack = ActionContext.getContext().getValueStack().findValue("someFieldName", String.class);
		
		assertEquals(valueFromStack, null);
	}
