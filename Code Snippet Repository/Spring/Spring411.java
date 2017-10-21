	@Test
	public void testUnionOfSpecificGetters() {
		Pointcut union = Pointcuts.union(allClassGetAgePointcut, allClassGetNamePointcut);
		assertFalse(Pointcuts.matches(union, TEST_BEAN_SET_AGE, TestBean.class, new Integer(6)));
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_AGE, TestBean.class));
		assertFalse(Pointcuts.matches(allClassGetAgePointcut, TEST_BEAN_GET_NAME, TestBean.class));
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_NAME, TestBean.class));
		assertFalse(Pointcuts.matches(union, TEST_BEAN_ABSQUATULATE, TestBean.class));

		// Union with all setters
		union = Pointcuts.union(union, allClassSetterPointcut);
		assertTrue(Pointcuts.matches(union, TEST_BEAN_SET_AGE, TestBean.class, new Integer(6)));
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_AGE, TestBean.class));
		assertFalse(Pointcuts.matches(allClassGetAgePointcut, TEST_BEAN_GET_NAME, TestBean.class));
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_NAME, TestBean.class));
		assertFalse(Pointcuts.matches(union, TEST_BEAN_ABSQUATULATE, TestBean.class));

		assertTrue(Pointcuts.matches(union, TEST_BEAN_SET_AGE, TestBean.class, new Integer(6)));
	}
