	@Test
	public void testConstructorInjectionWithCustomSetAsBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(CustomSetConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		RootBeanDefinition tbs = new RootBeanDefinition(CustomCollectionFactoryMethods.class);
		tbs.setUniqueFactoryMethodName("testBeanSet");
		bf.registerBeanDefinition("myTestBeanSet", tbs);

		CustomSetConstructorInjectionBean bean = (CustomSetConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanSet"), bean.getTestBeanSet());
		bean = (CustomSetConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(bf.getBean("myTestBeanSet"), bean.getTestBeanSet());
	}
