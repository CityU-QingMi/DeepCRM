	@Test
	public void testWithOptimizedProxy() throws Exception {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(OPTIMIZED_CONTEXT, CLASS);

		ITestBean testBean = (ITestBean) beanFactory.getBean("optimizedTestBean");
		assertTrue(AopUtils.isAopProxy(testBean));

		CountingBeforeAdvice beforeAdvice = (CountingBeforeAdvice) beanFactory.getBean("countingAdvice");

		testBean.setAge(23);
		testBean.getAge();

		assertEquals("Incorrect number of calls to proxy", 2, beforeAdvice.getCalls());
	}
