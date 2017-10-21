	@Test
	public void testSingleConstructorWithProvidedArgument() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(ProvidedArgumentBean.class);
		bd.getConstructorArgumentValues().addGenericArgumentValue(Collections.singletonList("value"));
		bf.registerBeanDefinition("beanWithArgs", bd);
		assertNotNull(bf.getBean(ProvidedArgumentBean.class));
	}
