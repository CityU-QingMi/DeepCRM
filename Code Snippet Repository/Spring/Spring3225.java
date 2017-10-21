	@Test
	public void testResourceInjectionWithPrototypes() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		bpp.setResourceFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition abd = new RootBeanDefinition(ResourceInjectionBean.class);
		abd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("annotatedBean", abd);
		RootBeanDefinition tbd1 = new RootBeanDefinition(TestBean.class);
		tbd1.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("testBean", tbd1);
		RootBeanDefinition tbd2 = new RootBeanDefinition(TestBean.class);
		tbd2.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		bf.registerBeanDefinition("testBean2", tbd2);

		ResourceInjectionBean bean = (ResourceInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.initCalled);
		assertTrue(bean.init2Called);
		assertTrue(bean.init3Called);

		TestBean tb = bean.getTestBean();
		TestBean tb2 = bean.getTestBean2();
		assertNotNull(tb);
		assertNotNull(tb2);

		ResourceInjectionBean anotherBean = (ResourceInjectionBean) bf.getBean("annotatedBean");
		assertNotSame(anotherBean, bean);
		assertNotSame(anotherBean.getTestBean(), tb);
		assertNotSame(anotherBean.getTestBean2(), tb2);

		bf.destroyBean("annotatedBean", bean);
		assertTrue(bean.destroyCalled);
		assertTrue(bean.destroy2Called);
		assertTrue(bean.destroy3Called);
	}
