	@Test
	public void testExtendedEjbInjection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerResolvableDependency(BeanFactory.class, bf);

		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ExtendedEjbInjectionBean.class));
		TestBean tb = new TestBean();
		bf.registerSingleton("testBean", tb);
		TestBean tb2 = new TestBean();
		bf.registerSingleton("testBean2", tb2);
		TestBean tb3 = new TestBean();
		bf.registerSingleton("testBean3", tb3);
		TestBean tb4 = new TestBean();
		bf.registerSingleton("testBean4", tb4);
		NestedTestBean tb6 = new NestedTestBean();
		bf.registerSingleton("xy", tb6);
		bf.registerAlias("xy", "testBean9");

		ExtendedEjbInjectionBean bean = (ExtendedEjbInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.initCalled);
		assertTrue(bean.init2Called);
		assertSame(tb, bean.getTestBean());
		assertSame(tb2, bean.getTestBean2());
		assertSame(tb4, bean.getTestBean3());
		assertSame(tb3, bean.getTestBean4());
		assertSame(tb6, bean.testBean5);
		assertSame(tb6, bean.testBean6);
		assertSame(bf, bean.beanFactory);

		bf.destroySingletons();
		assertTrue(bean.destroyCalled);
		assertTrue(bean.destroy2Called);
	}
