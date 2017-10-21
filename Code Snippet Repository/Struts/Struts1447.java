	public void testAddAddress() {
        ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> stackContext = stack.getContext();
		stackContext.put(ReflectionContextState.CREATE_NULL_OBJECTS, Boolean.TRUE);
		stackContext.put(ReflectionContextState.DENY_METHOD_EXECUTION, Boolean.TRUE);
		stackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

		PersonAction action = new PersonAction();
		stack.push(action);

		stack.setValue("address", "2 Chandos Court:61 Haverstock Hill:London:England");
		assertNotNull(action.getAddress());
		assertEquals(1, action.getAddress().size());

		for(Object address : action.getAddress()) {
			System.out.println("Address: " + address);
		}

		assertEquals(Address.class, action.getAddress().get(0).getClass());
		assertEquals("2 Chandos Court", action.getAddress().get(0).getLine1());
		assertEquals("61 Haverstock Hill", action.getAddress().get(0).getLine2());
		assertEquals("London", action.getAddress().get(0).getCity());
		assertEquals("England", action.getAddress().get(0).getCountry());
	}
