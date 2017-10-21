	@Test
	@SuppressWarnings("")
	public void testSunnyDayPath() throws Exception {
		given(beanFactory.getType(BEAN_NAME)).willReturn((Class)String.class);
		factory.setTargetBeanName(BEAN_NAME);
		factory.setMethodName("toString()");
		factory.setBeanFactory(beanFactory);
		Object result = factory.getObject();
		assertNotNull(result);
		assertTrue(result instanceof Method);
		Method method = (Method) result;
		assertEquals("Bingo", method.invoke("Bingo"));
	}
