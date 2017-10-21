	private GenericApplicationContext createContext(Class<?> configClass) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		if (customScope != null) {
			beanFactory.registerScope(SCOPE, customScope);
		}
		beanFactory.registerBeanDefinition("config", new RootBeanDefinition(configClass));
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(beanFactory);
		ctx.refresh();
		return ctx;
	}
