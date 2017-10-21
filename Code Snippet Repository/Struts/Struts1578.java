	public void testGetterSetterGetsCalledApropriately1() throws Exception {
		
		validator1.setRepopulateField(true);
		validator1.validate(action);

		
		ActionContext.getContext().getActionInvocation().invoke();
		
		Object valueFromStack = ActionContext.getContext().getValueStack().findValue("someFieldName", String.class);
		
		assertEquals(valueFromStack, "some value");
	}
