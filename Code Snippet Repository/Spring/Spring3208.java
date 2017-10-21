	@Test
	public void testResourceInjectionWithDefaultMethod() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(DefaultMethodResourceInjectionBean.class));
		TestBean tb2 = new TestBean();
		bf.registerSingleton("testBean2", tb2);
		NestedTestBean tb7 = new NestedTestBean();
		bf.registerSingleton("testBean7", tb7);

		DefaultMethodResourceInjectionBean bean = (DefaultMethodResourceInjectionBean) bf.getBean("annotatedBean");
		assertSame(tb2, bean.getTestBean2());
		assertSame(2, bean.counter);

		bf.destroySingletons();
		assertSame(3, bean.counter);
	}
