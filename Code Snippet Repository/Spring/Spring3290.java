	@Test
	public void postProcessorDoesNotOverrideRegularBeanDefinitionsEvenWithScopedProxy() {
		RootBeanDefinition rbd = new RootBeanDefinition(TestBean.class);
		rbd.setResource(new DescriptiveResource("XML or something"));
		BeanDefinitionHolder proxied = ScopedProxyUtils.createScopedProxy(
				new BeanDefinitionHolder(rbd, "bar"), beanFactory, true);
		beanFactory.registerBeanDefinition("bar", proxied.getBeanDefinition());
		beanFactory.registerBeanDefinition("config", new RootBeanDefinition(SingletonBeanConfig.class));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);
		beanFactory.getBean("foo", Foo.class);
		beanFactory.getBean("bar", TestBean.class);
	}
