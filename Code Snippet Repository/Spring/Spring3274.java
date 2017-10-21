	@Test
	public void genericsBasedInjectionWithLateGenericsMatchingOnJdkProxy() {
		beanFactory.registerBeanDefinition("configClass", new RootBeanDefinition(RepositoryConfiguration.class));
		new ConfigurationClassPostProcessor().postProcessBeanFactory(beanFactory);
		DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		autoProxyCreator.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(autoProxyCreator);
		beanFactory.registerSingleton("traceInterceptor", new DefaultPointcutAdvisor(new SimpleTraceInterceptor()));
		beanFactory.preInstantiateSingletons();

		String[] beanNames = beanFactory.getBeanNamesForType(RepositoryInterface.class);
		assertTrue(ObjectUtils.containsElement(beanNames, "stringRepo"));

		beanNames = beanFactory.getBeanNamesForType(ResolvableType.forClassWithGenerics(RepositoryInterface.class, String.class));
		assertEquals(1, beanNames.length);
		assertEquals("stringRepo", beanNames[0]);

		assertTrue(AopUtils.isJdkDynamicProxy(beanFactory.getBean("stringRepo")));
	}
