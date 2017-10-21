    public void testMemberObject() throws Exception {
    	ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "memberObject", null, null);
    	MemberObject action = (MemberObject) baseActionProxy.getAction();
    	action.getPerson().setName(null);
    	action.getPerson().setEmail(null);
    	action.getPerson().getAddress().setStreet("short");
    	baseActionProxy.execute();

    	Map<String, List<String>> fieldErrors = ((ValidationAware) baseActionProxy.getAction()).getFieldErrors();
    	assertNotNull(fieldErrors);
    	assertEquals(5, fieldErrors.size()); // 5: as there will be field errors for 'person' and 'person.address' themselves
    	assertValue(fieldErrors, "person.name", Arrays.asList("name cannot be null"));
    	assertValue(fieldErrors, "person.email", Arrays.asList("email cannot be null"));
    	assertValue(fieldErrors, "person.address.street", Arrays.asList("street cannot be smaller than 7 characters"));
    }
