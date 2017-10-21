	public void testAddAddressesNoGenericElementAnnotation() {
        ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> stackContext = stack.getContext();
		stackContext.put(ReflectionContextState.CREATE_NULL_OBJECTS, Boolean.TRUE);
		stackContext.put(ReflectionContextState.DENY_METHOD_EXECUTION, Boolean.TRUE);
		stackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

		PersonAction action = new PersonAction();
		stack.push(action);

		stack.setValue("addressesNoGenericElementAnnotation", "2 Chandos Court:61 Haverstock Hill:London:England");
		assertNotNull(action.getAddressesNoGenericElementAnnotation());
		assertEquals(1, action.getAddressesNoGenericElementAnnotation().size());

		for(Object address : action.getAddressesNoGenericElementAnnotation()) {
			System.out.println("Address: " + address);
		}

		assertEquals(Address.class, action.getAddressesNoGenericElementAnnotation().get(0).getClass());
		assertEquals("2 Chandos Court", ((Address)action.getAddressesNoGenericElementAnnotation().get(0)).getLine1());
		assertEquals("61 Haverstock Hill", ((Address)action.getAddressesNoGenericElementAnnotation().get(0)).getLine2());
		assertEquals("London", ((Address)action.getAddressesNoGenericElementAnnotation().get(0)).getCity());
		assertEquals("England", ((Address)action.getAddressesNoGenericElementAnnotation().get(0)).getCountry());
	}
