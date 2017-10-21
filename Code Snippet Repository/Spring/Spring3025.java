	@Test
	public void testChainedDecorators() throws Exception {
		ITestBean bean = (ITestBean) this.beanFactory.getBean("chainedTestBean");
		assertTestBean(bean);
		assertTrue(AopUtils.isAopProxy(bean));
		Advisor[] advisors = ((Advised) bean).getAdvisors();
		assertEquals("Incorrect number of advisors", 2, advisors.length);
		assertEquals("Incorrect advice class", DebugInterceptor.class, advisors[0].getAdvice().getClass());
		assertEquals("Incorrect advice class", NopInterceptor.class, advisors[1].getAdvice().getClass());
	}
