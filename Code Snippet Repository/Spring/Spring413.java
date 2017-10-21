	@Test
	public void testIntersectionOfSpecificGettersAndSubclassGetters() {
		assertTrue(Pointcuts.matches(allClassGetAgePointcut, TEST_BEAN_GET_AGE, TestBean.class));
		assertTrue(Pointcuts.matches(allClassGetAgePointcut, TEST_BEAN_GET_AGE, MyTestBean.class));
		assertFalse(Pointcuts.matches(myTestBeanGetterPointcut, TEST_BEAN_GET_NAME, TestBean.class));
		assertFalse(Pointcuts.matches(myTestBeanGetterPointcut, TEST_BEAN_GET_AGE, TestBean.class));
		assertTrue(Pointcuts.matches(myTestBeanGetterPointcut, TEST_BEAN_GET_NAME, MyTestBean.class));
		assertTrue(Pointcuts.matches(myTestBeanGetterPointcut, TEST_BEAN_GET_AGE, MyTestBean.class));

		Pointcut intersection = Pointcuts.intersection(allClassGetAgePointcut, myTestBeanGetterPointcut);
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_NAME, TestBean.class));
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_AGE, TestBean.class));
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_NAME, MyTestBean.class));
		assertTrue(Pointcuts.matches(intersection, TEST_BEAN_GET_AGE, MyTestBean.class));
		// Matches subclass of MyTestBean
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_NAME, MyTestBeanSubclass.class));
		assertTrue(Pointcuts.matches(intersection, TEST_BEAN_GET_AGE, MyTestBeanSubclass.class));

		// Now intersection with MyTestBeanSubclass getters should eliminate MyTestBean target
		intersection = Pointcuts.intersection(intersection, myTestBeanSubclassGetterPointcut);
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_NAME, TestBean.class));
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_AGE, TestBean.class));
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_NAME, MyTestBean.class));
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_AGE, MyTestBean.class));
		// Still matches subclass of MyTestBean
		assertFalse(Pointcuts.matches(intersection, TEST_BEAN_GET_NAME, MyTestBeanSubclass.class));
		assertTrue(Pointcuts.matches(intersection, TEST_BEAN_GET_AGE, MyTestBeanSubclass.class));

		// Now union with all TestBean methods
		Pointcut union = Pointcuts.union(intersection, allTestBeanMethodsPointcut);
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_NAME, TestBean.class));
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_AGE, TestBean.class));
		assertFalse(Pointcuts.matches(union, TEST_BEAN_GET_NAME, MyTestBean.class));
		assertFalse(Pointcuts.matches(union, TEST_BEAN_GET_AGE, MyTestBean.class));
		// Still matches subclass of MyTestBean
		assertFalse(Pointcuts.matches(union, TEST_BEAN_GET_NAME, MyTestBeanSubclass.class));
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_AGE, MyTestBeanSubclass.class));

		assertTrue(Pointcuts.matches(union, TEST_BEAN_ABSQUATULATE, TestBean.class));
		assertFalse(Pointcuts.matches(union, TEST_BEAN_ABSQUATULATE, MyTestBean.class));
	}
