    public void testTwoCheckboxNoValue() throws Exception {
		param.put("user", "batman");
		param.put("email", "batman@comic.org");
		param.put("__checkbox_superpower", new String[]{"true", "true"});

		prepare(ai);

		interceptor.init();
		interceptor.intercept(ai);
		interceptor.destroy();

		HttpParameters parameters = ai.getInvocationContext().getParameters();
		assertFalse(parameters.contains("__checkbox_superpower"));
		assertEquals(2, parameters.keySet().size()); // should be 2 as __checkbox_ should be removed
		assertFalse(parameters.get("superpower").isDefined());
    }
