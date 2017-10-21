	@Test
	public void configureBeanPerformsAutowiringByNameIfAppropriateBeanWiringInfoResolverIsPluggedIn() throws Exception {
		TestBean beanInstance = new TestBean();
		// spouse for autowiring by name...
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(TestBean.class);
		builder.addConstructorArgValue("David Gavurin");

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.registerBeanDefinition("spouse", builder.getBeanDefinition());

		BeanWiringInfoResolver resolver = mock(BeanWiringInfoResolver.class);
		given(resolver.resolveWiringInfo(beanInstance)).willReturn(new BeanWiringInfo(BeanWiringInfo.AUTOWIRE_BY_NAME, false));

		BeanConfigurerSupport configurer = new StubBeanConfigurerSupport();
		configurer.setBeanFactory(factory);
		configurer.setBeanWiringInfoResolver(resolver);
		configurer.configureBean(beanInstance);
		assertEquals("Bean is evidently not being configured (for some reason)", "David Gavurin", beanInstance.getSpouse().getName());
	}
