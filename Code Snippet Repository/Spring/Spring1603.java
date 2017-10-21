	@Test
	public void configureBeanDoesNothingIfBeanWiringInfoResolverResolvesToNull() throws Exception {
		TestBean beanInstance = new TestBean();

		BeanWiringInfoResolver resolver = mock(BeanWiringInfoResolver.class);

		BeanConfigurerSupport configurer = new StubBeanConfigurerSupport();
		configurer.setBeanWiringInfoResolver(resolver);
		configurer.setBeanFactory(new DefaultListableBeanFactory());
		configurer.configureBean(beanInstance);
		verify(resolver).resolveWiringInfo(beanInstance);
		assertNull(beanInstance.getName());
	}
