	public void testOneDateTextField() throws Exception {
		param.put("__year_name", new String[]{"2000"});
		param.put("__month_name", new String[]{"06"});
		param.put("__day_name", new String[]{"15"});

		ActionContext.getContext().setParameters(HttpParameters.create(param).build());

		interceptor.init();
		interceptor.intercept(ai);
		interceptor.destroy();

		HttpParameters parameters = ai.getInvocationContext().getParameters();
		assertFalse(parameters.contains("__year_name"));
		assertFalse(parameters.contains("__month_name"));
		assertFalse(parameters.contains("__day_name"));
		assertTrue(parameters.contains("name"));
		assertEquals(1, parameters.keySet().size());
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-06-15"); 
		assertEquals(date, parameters.get("name").getObject());
	}
