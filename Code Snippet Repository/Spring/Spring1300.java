	@Test
	public void testConstructorInjectionWithPlainSetAsBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(SetConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		RootBeanDefinition tbs = new RootBeanDefinition(CollectionFactoryMethods.class);
		tbs.setUniqueFactoryMethodName("testBeanSet");
		bf.registerBeanDefinition("myTestBeanSet", tbs);
		bf.registerSingleton("otherSet", new HashSet<>());

		SetConstructorInjectionBean bean = (SetConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanSet"), bean.getTestBeanSet());
		bean = (SetConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanSet"), bean.getTestBeanSet());
	}
