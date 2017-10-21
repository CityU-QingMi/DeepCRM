	@Test
	public void testConstructorResourceInjectionWithNullFromFactoryMethod() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		bf.registerResolvableDependency(BeanFactory.class, bf);
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(ConstructorResourceInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		RootBeanDefinition tb = new RootBeanDefinition(NullFactoryMethods.class);
		tb.setFactoryMethodName("createTestBean");
		bf.registerBeanDefinition("testBean", tb);
		RootBeanDefinition ntb = new RootBeanDefinition(NullFactoryMethods.class);
		ntb.setFactoryMethodName("createNestedTestBean");
		bf.registerBeanDefinition("nestedTestBean", ntb);
		bf.registerSingleton("nestedTestBean2", new NestedTestBean());

		ConstructorResourceInjectionBean bean = (ConstructorResourceInjectionBean) bf.getBean("annotatedBean");
		assertNull(bean.getTestBean());
		assertNull(bean.getTestBean2());
		assertNull(bean.getTestBean3());
		assertNull(bean.getTestBean4());
		assertNull(bean.getNestedTestBean());
		assertSame(bf, bean.getBeanFactory());

		bean = (ConstructorResourceInjectionBean) bf.getBean("annotatedBean");
		assertNull(bean.getTestBean());
		assertNull(bean.getTestBean2());
		assertNull(bean.getTestBean3());
		assertNull(bean.getTestBean4());
		assertNull(bean.getNestedTestBean());
		assertSame(bf, bean.getBeanFactory());
	}
