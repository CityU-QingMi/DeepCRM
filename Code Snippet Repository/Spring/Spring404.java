	@Test
	public void testDynamicAndStaticMethodMatcherIntersection() throws Exception {
		MethodMatcher mm1 = MethodMatcher.TRUE;
		MethodMatcher mm2 = new TestDynamicMethodMatcherWhichMatches();
		MethodMatcher intersection = MethodMatchers.intersection(mm1, mm2);
		assertTrue("Intersection is a dynamic matcher", intersection.isRuntime());
		assertTrue("2Matched setAge method", intersection.matches(ITESTBEAN_SETAGE, TestBean.class));
		assertTrue("3Matched setAge method", intersection.matches(ITESTBEAN_SETAGE, TestBean.class, new Integer(5)));
		// Knock out dynamic part
		intersection = MethodMatchers.intersection(intersection, new TestDynamicMethodMatcherWhichDoesNotMatch());
		assertTrue("Intersection is a dynamic matcher", intersection.isRuntime());
		assertTrue("2Matched setAge method", intersection.matches(ITESTBEAN_SETAGE, TestBean.class));
		assertFalse("3 - not Matched setAge method", intersection.matches(ITESTBEAN_SETAGE, TestBean.class, new Integer(5)));
	}
