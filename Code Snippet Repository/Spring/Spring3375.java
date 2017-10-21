	@Test
	public void testProcessImportsWithAsm() {
		int configClasses = 2;
		int beansInClasses = 2;
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("config", new RootBeanDefinition(ConfigurationWithImportAnnotation.class.getName()));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);
		assertThat(beanFactory.getBeanDefinitionCount(), equalTo(configClasses + beansInClasses));
	}
