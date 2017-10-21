	public void testAddPerson() {
        ValueStack stack = ActionContext.getContext().getValueStack();

        Map<String, Object> stackContext = stack.getContext();
        stackContext.put(ReflectionContextState.CREATE_NULL_OBJECTS, Boolean.TRUE);
        stackContext.put(ReflectionContextState.DENY_METHOD_EXECUTION, Boolean.TRUE);
        stackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        PersonAction action = new PersonAction();
        stack.push(action);

        stack.setValue("users", "jonathan:gerrish");
        assertNotNull(action.getUsers());
        assertEquals(1, action.getUsers().size());
        
        for(Object person : action.getUsers()) {
        	System.out.println("Person: " + person);
        }
        
        assertEquals(Person.class, action.getUsers().get(0).getClass());
        assertEquals("jonathan", action.getUsers().get(0).getFirstName());
        assertEquals("gerrish", action.getUsers().get(0).getLastName());
	}
