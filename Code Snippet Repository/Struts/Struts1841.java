	public void testOneCheckboxTrue() throws Exception {
		param.put("user", "batman");
		param.put("email", "batman@comic.org");
		param.put("superpower", "true");
		param.put("__checkbox_superpower", "true");
		assertTrue(param.containsKey("__checkbox_superpower"));

		prepare(ai);

		interceptor.init();
		interceptor.intercept(ai);
		interceptor.destroy();

		HttpParameters parameters = ai.getInvocationContext().getParameters();
		assertFalse(parameters.contains("__checkbox_superpower"));
		assertEquals(3, parameters.keySet().size()); // should be 3 as __checkbox_ should be removed
		assertEquals("true", parameters.get("superpower").getValue());
	}
