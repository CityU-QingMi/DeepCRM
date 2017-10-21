	private DefaultListableBeanFactory createBeanFactoryAndRegisterBean(final Class<?> beanClass,
			final String initMethodName, final String destroyMethodName) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		RootBeanDefinition beanDefinition = new RootBeanDefinition(beanClass);
		beanDefinition.setInitMethodName(initMethodName);
		beanDefinition.setDestroyMethodName(destroyMethodName);
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		beanFactory.registerBeanDefinition(LIFECYCLE_TEST_BEAN, beanDefinition);
		return beanFactory;
	}
