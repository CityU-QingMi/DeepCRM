	@Test
	public void testImportAnnotationWithMultipleArgumentsResultingInOverriddenBeanDefinition() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("config", new RootBeanDefinition(
				WithMultipleArgumentsThatWillCauseDuplication.class));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);
		assertThat(beanFactory.getBeanDefinitionCount(), equalTo(4));
		assertThat(beanFactory.getBean("foo", ITestBean.class).getName(), equalTo("foo2"));
	}
