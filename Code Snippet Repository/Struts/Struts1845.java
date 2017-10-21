    public void testTwoCheckboxMixed() throws Exception {
		param.put("user", "batman");
		param.put("email", "batman@comic.org");
		param.put("__checkbox_superpower", "true");
		param.put("superpower", "yes");
		param.put("__checkbox_cool", "no");
		assertTrue(param.containsKey("__checkbox_superpower"));
		assertTrue(param.containsKey("__checkbox_cool"));

		prepare(ai);

		interceptor.init();
		interceptor.intercept(ai);
		interceptor.destroy();

		HttpParameters parameters = ai.getInvocationContext().getParameters();
		assertFalse(parameters.contains("__checkbox_superpower"));
		assertFalse(parameters.contains("__checkbox_cool"));
		assertEquals(4, parameters.keySet().size()); // should be 4 as __checkbox_ should be removed
		assertEquals("yes", parameters.get("superpower").getValue());
		assertEquals("false", parameters.get("cool").getValue()); // will use false as default and not 'no'
	}
