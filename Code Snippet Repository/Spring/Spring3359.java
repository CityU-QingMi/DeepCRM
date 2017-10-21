	private void assertAdviceWasApplied(Class<?> configClass) {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(
				new ClassPathResource("aspectj-autoproxy-config.xml", ConfigurationClassAspectIntegrationTests.class));
		GenericApplicationContext ctx = new GenericApplicationContext(factory);
		ctx.addBeanFactoryPostProcessor(new ConfigurationClassPostProcessor());
		ctx.registerBeanDefinition("config", new RootBeanDefinition(configClass));
		ctx.refresh();

		TestBean testBean = ctx.getBean("testBean", TestBean.class);
		assertThat(testBean.getName(), equalTo("name"));
		testBean.absquatulate();
		assertThat(testBean.getName(), equalTo("advisedName"));
	}
