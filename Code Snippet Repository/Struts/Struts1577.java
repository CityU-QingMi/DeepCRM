	public void testUseFullFieldName() throws Exception {
		validator2.setRepopulateField(true);
		validator2.validate(action);
		
		ActionContext.getContext().getActionInvocation().invoke();
		Object valueFromStack1 = ActionContext.getContext().getValueStack().findValue("someFieldName", String.class);
		Object valueFromStack2 = ActionContext.getContext().getValueStack().findValue("xxxsomeFieldName", String.class);
		
		assertNull(valueFromStack1);
		assertEquals(valueFromStack2, "some value");
	}
