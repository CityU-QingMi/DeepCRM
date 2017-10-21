	@Test
	public void testStaticMethodMatcherUnion() throws Exception {
		MethodMatcher getterMatcher = new StartsWithMatcher("get");
		MethodMatcher setterMatcher = new StartsWithMatcher("set");
		MethodMatcher union = MethodMatchers.union(getterMatcher, setterMatcher);

		assertFalse("Union is a static matcher", union.isRuntime());
		assertTrue("Matched setAge method", union.matches(ITESTBEAN_SETAGE, TestBean.class));
		assertTrue("Matched getAge method", union.matches(ITESTBEAN_GETAGE, TestBean.class));
		assertFalse("Didn't matched absquatulate method", union.matches(IOTHER_ABSQUATULATE, TestBean.class));
	}
