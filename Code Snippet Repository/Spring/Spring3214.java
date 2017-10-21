	@Test
	public void testLazyResolutionWithCglibProxy() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);

		bf.registerBeanDefinition("annotatedBean", new RootBeanDefinition(LazyResourceCglibInjectionBean.class));
		bf.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

		LazyResourceCglibInjectionBean bean = (LazyResourceCglibInjectionBean) bf.getBean("annotatedBean");
		assertFalse(bf.containsSingleton("testBean"));
		bean.testBean.setName("notLazyAnymore");
		assertTrue(bf.containsSingleton("testBean"));
		TestBean tb = (TestBean) bf.getBean("testBean");
		assertEquals("notLazyAnymore", tb.getName());
	}
