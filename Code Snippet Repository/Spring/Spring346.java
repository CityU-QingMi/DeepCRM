	@Test
	public void testSingletonProxyWithPrototypeTarget() {
		TestBeanImpl.constructionCount = 0;
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(CONTEXT);
		for (int i = 0; i < 10; i++) {
			TestBean tb = (TestBean) bf.getBean("testBeanSingleton");
			tb.doSomething();
		}
		TestInterceptor interceptor = (TestInterceptor) bf.getBean("testInterceptor");
		assertEquals(1, TestBeanImpl.constructionCount);
		assertEquals(10, interceptor.invocationCount);
	}
