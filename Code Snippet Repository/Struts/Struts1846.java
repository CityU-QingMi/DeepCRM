	public void testTwoCheckboxMixedWithDifferentDefault() throws Exception {
		param.put("user", "batman");
		param.put("email", "batman@comic.org");
		param.put("__checkbox_superpower", "true");
		param.put("superpower", "yes");
		param.put("__checkbox_cool", "no");
		assertTrue(param.containsKey("__checkbox_superpower"));
		assertTrue(param.containsKey("__checkbox_cool"));

		prepare(ai);

		interceptor.setUncheckedValue("no");
		interceptor.init();
		interceptor.intercept(ai);
		interceptor.destroy();

		HttpParameters parameters = ai.getInvocationContext().getParameters();
		assertFalse(parameters.contains("__checkbox_superpower"));
		assertFalse(parameters.contains("__checkbox_cool"));
		assertEquals(4, parameters.keySet().size()); // should be 4 as __checkbox_ should be removed
		assertEquals("yes", parameters.get("superpower").getValue());
		assertEquals("no", parameters.get("cool").getValue());
	}
