	@Test
	public void testSingle() throws Exception {
		MethodMatcher defaultMm = MethodMatcher.TRUE;
		assertTrue(defaultMm.matches(EXCEPTION_GETMESSAGE, Exception.class));
		assertTrue(defaultMm.matches(ITESTBEAN_SETAGE, TestBean.class));
		defaultMm = MethodMatchers.intersection(defaultMm, new StartsWithMatcher("get"));

		assertTrue(defaultMm.matches(EXCEPTION_GETMESSAGE, Exception.class));
		assertFalse(defaultMm.matches(ITESTBEAN_SETAGE, TestBean.class));
	}
