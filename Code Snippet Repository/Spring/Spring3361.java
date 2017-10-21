	private void aliasesAreRespected(Class<?> testClass, Supplier<TestBean> testBeanSupplier, String beanName) {
		TestBean testBean = testBeanSupplier.get();
		BeanFactory factory = initBeanFactory(testClass);

		assertSame(testBean, factory.getBean(beanName));
		Arrays.stream(factory.getAliases(beanName)).map(factory::getBean).forEach(alias -> assertSame(testBean, alias));

		// method name should not be registered
		exception.expect(NoSuchBeanDefinitionException.class);
		factory.getBean("methodName");
	}
