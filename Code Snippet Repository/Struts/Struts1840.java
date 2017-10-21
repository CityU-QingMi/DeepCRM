	public void testPassthroughTwo() throws Exception {
		param.put("user", "batman");
		param.put("email", "batman@comic.org");

		prepare(ai);

		interceptor.init();
		interceptor.intercept(ai);
		interceptor.destroy();
		
		assertEquals(2, ai.getInvocationContext().getParameters().keySet().size());
	}
