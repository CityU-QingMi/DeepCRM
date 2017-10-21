	@Test
	public void testGenericsBasedInjectionWithBeanDefinitionTargetResolvableType() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd1 = new RootBeanDefinition(GenericInterface2Bean.class);
		bd1.setTargetType(ResolvableType.forClassWithGenerics(GenericInterface2Bean.class, String.class));
		bf.registerBeanDefinition("bean1", bd1);
		RootBeanDefinition bd2 = new RootBeanDefinition(GenericInterface2Bean.class);
		bd2.setTargetType(ResolvableType.forClassWithGenerics(GenericInterface2Bean.class, Integer.class));
		bf.registerBeanDefinition("bean2", bd2);
		bf.registerBeanDefinition("bean3", new RootBeanDefinition(MultiGenericFieldInjection.class));

		assertEquals("bean1 a bean2 123", bf.getBean("bean3").toString());
	}
