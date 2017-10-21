	private DefaultListableBeanFactory initBeanFactory(Class<?>... configClasses) {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		for (Class<?> configClass : configClasses) {
			String configBeanName = configClass.getName();
			factory.registerBeanDefinition(configBeanName, new RootBeanDefinition(configClass));
		}
		ConfigurationClassPostProcessor ccpp = new ConfigurationClassPostProcessor();
		ccpp.postProcessBeanDefinitionRegistry(factory);
		ccpp.postProcessBeanFactory(factory);
		RequiredAnnotationBeanPostProcessor rapp = new RequiredAnnotationBeanPostProcessor();
		rapp.setBeanFactory(factory);
		factory.addBeanPostProcessor(rapp);
		factory.freezeConfiguration();
		return factory;
	}
