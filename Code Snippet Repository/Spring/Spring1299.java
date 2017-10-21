	@Test
	public void testConstructorInjectionWithTypedSetAsBean() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd = new RootBeanDefinition(SetConstructorInjectionBean.class);
		bd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", bd);
		MyTestBeanSet tbs = new MyTestBeanSet();
		tbs.add(new TestBean("tb1"));
		tbs.add(new TestBean("tb2"));
		bf.registerSingleton("testBeans", tbs);
		bf.registerSingleton("otherSet", new HashSet<>());

		SetConstructorInjectionBean bean = (SetConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(tbs, bean.getTestBeanSet());
		bean = (SetConstructorInjectionBean) bf.getBean("annotatedBean");
		assertSame(tbs, bean.getTestBeanSet());
	}
