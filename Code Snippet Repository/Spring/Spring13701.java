	@Test
	public void freeMarkerConfigurationAsBean() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		RootBeanDefinition loaderDef = new RootBeanDefinition(SpringTemplateLoader.class);
		loaderDef.getConstructorArgumentValues().addGenericArgumentValue(new DefaultResourceLoader());
		loaderDef.getConstructorArgumentValues().addGenericArgumentValue("/freemarker");
		RootBeanDefinition configDef = new RootBeanDefinition(Configuration.class);
		configDef.getPropertyValues().add("templateLoader", loaderDef);
		beanFactory.registerBeanDefinition("freeMarkerConfig", configDef);
		beanFactory.getBean(Configuration.class);
	}
