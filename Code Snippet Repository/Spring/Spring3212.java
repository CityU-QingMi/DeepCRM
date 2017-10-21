	@Test
	public void testLazyResolutionWithResourceField() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);

		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(LazyResourceFieldInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

		LazyResourceFieldInjectionBean bean = (LazyResourceFieldInjectionBean) bf.getBean("annotatedBean");
		assertFalse(bf.containsSingleton("testBean"));
		bean.testBean.setName("notLazyAnymore");
		assertTrue(bf.containsSingleton("testBean"));
		TestBean tb = (TestBean) bf.getBean("testBean");
		assertEquals("notLazyAnymore", tb.getName());
	}
