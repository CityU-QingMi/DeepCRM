	private void customBeanNameIsRespected(Class<?> testClass, Supplier<TestBean> testBeanSupplier, String beanName) {
		GenericApplicationContext ac = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(ac);
		ac.registerBeanDefinition("config", new RootBeanDefinition(testClass));
		ac.refresh();

		assertSame(testBeanSupplier.get(), ac.getBean(beanName));

		// method name should not be registered
		exception.expect(NoSuchBeanDefinitionException.class);
		ac.getBean("methodName");
	}
