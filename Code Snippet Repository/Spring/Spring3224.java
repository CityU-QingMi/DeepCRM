	@Test
	public void testResourceInjection() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		bpp.setResourceFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ResourceInjectionBean.class));
		TestBean tb = new TestBean();
		bf.registerSingleton("testBean", tb);
		TestBean tb2 = new TestBean();
		bf.registerSingleton("testBean2", tb2);

		ResourceInjectionBean bean = (ResourceInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.initCalled);
		assertTrue(bean.init2Called);
		assertTrue(bean.init3Called);
		assertSame(tb, bean.getTestBean());
		assertSame(tb2, bean.getTestBean2());
		bf.destroySingletons();
		assertTrue(bean.destroyCalled);
		assertTrue(bean.destroy2Called);
		assertTrue(bean.destroy3Called);
	}
