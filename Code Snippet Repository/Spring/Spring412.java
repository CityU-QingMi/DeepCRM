	@Test
	public void testUnionOfAllSettersAndSubclassSetters() {
		assertFalse(Pointcuts.matches(myTestBeanSetterPointcut, TEST_BEAN_SET_AGE, TestBean.class, new Integer(6)));
		assertTrue(Pointcuts.matches(myTestBeanSetterPointcut, TEST_BEAN_SET_AGE, MyTestBean.class, new Integer(6)));
		assertFalse(Pointcuts.matches(myTestBeanSetterPointcut, TEST_BEAN_GET_AGE, TestBean.class));

		Pointcut union = Pointcuts.union(myTestBeanSetterPointcut, allClassGetterPointcut);
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_AGE, TestBean.class));
		assertTrue(Pointcuts.matches(union, TEST_BEAN_GET_AGE, MyTestBean.class));
		// Still doesn't match superclass setter
		assertTrue(Pointcuts.matches(union, TEST_BEAN_SET_AGE, MyTestBean.class, new Integer(6)));
		assertFalse(Pointcuts.matches(union, TEST_BEAN_SET_AGE, TestBean.class, new Integer(6)));
	}
