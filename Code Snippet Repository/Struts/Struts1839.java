	public void testPassthroughOne() throws Exception {
		param.put("user", "batman");

		prepare(ai);

		interceptor.init();
		interceptor.intercept(ai);
		interceptor.destroy();
		
		assertEquals(1, ai.getInvocationContext().getParameters().keySet().size());
	}
