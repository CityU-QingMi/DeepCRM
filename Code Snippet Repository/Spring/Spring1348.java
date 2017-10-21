	@Test
	@SuppressWarnings("")
	public void testGenericsBasedInjectionIntoMatchingTypeVariable() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(GenericInterface1Impl.class);
		bd.setFactoryMethodName("create");
		bf.registerBeanDefinition("bean1", bd);
		bf.registerBeanDefinition("bean2", new RootBeanDefinition(GenericInterface2Impl.class));

		GenericInterface1Impl bean1 = (GenericInterface1Impl) bf.getBean("bean1");
		GenericInterface2Impl bean2 = (GenericInterface2Impl) bf.getBean("bean2");
		assertSame(bean2, bean1.gi2);
	}
