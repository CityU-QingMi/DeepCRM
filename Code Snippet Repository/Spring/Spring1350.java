	@Test
	@SuppressWarnings("")
	public void testGenericsBasedInjectionIntoTypeVariableSelectingBestMatch() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(GenericInterface1Impl.class);
		bd.setFactoryMethodName("create");
		bf.registerBeanDefinition("bean1", bd);
		bf.registerBeanDefinition("bean2", new RootBeanDefinition(GenericInterface2Impl.class));
		bf.registerBeanDefinition("bean2a", new RootBeanDefinition(ReallyGenericInterface2Impl.class));
		bf.registerBeanDefinition("bean2b", new RootBeanDefinition(PlainGenericInterface2Impl.class));

		GenericInterface1Impl bean1 = (GenericInterface1Impl) bf.getBean("bean1");
		GenericInterface2Impl bean2 = (GenericInterface2Impl) bf.getBean("bean2");
		assertSame(bean2, bean1.gi2);

		assertArrayEquals(new String[] {"bean1"}, bf.getBeanNamesForType(ResolvableType.forClassWithGenerics(GenericInterface1.class, String.class)));
		assertArrayEquals(new String[] {"bean2"}, bf.getBeanNamesForType(ResolvableType.forClassWithGenerics(GenericInterface2.class, String.class)));
	}
