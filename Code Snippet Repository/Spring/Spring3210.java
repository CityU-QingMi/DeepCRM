	@Test
	public void testResourceInjectionFromJndi() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		SimpleJndiBeanFactory resourceFactory = new SimpleJndiBeanFactory();
		ExpectedLookupTemplate jndiTemplate = new ExpectedLookupTemplate();
		TestBean tb = new TestBean();
		jndiTemplate.addObject("java:comp/env/testBean", tb);
		TestBean tb2 = new TestBean();
		jndiTemplate.addObject("java:comp/env/testBean2", tb2);
		resourceFactory.setJndiTemplate(jndiTemplate);
		bpp.setResourceFactory(resourceFactory);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(ResourceInjectionBean.class));

		ResourceInjectionBean bean = (ResourceInjectionBean) bf.getBean("annotatedBean");
		assertTrue(bean.initCalled);
		assertTrue(bean.init2Called);
		assertSame(tb, bean.getTestBean());
		assertSame(tb2, bean.getTestBean2());
		bf.destroySingletons();
		assertTrue(bean.destroyCalled);
		assertTrue(bean.destroy2Called);
	}
