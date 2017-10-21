	@Test
	public void testCircularReferenceWithPostProcessor() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(factory).loadBeanDefinitions(CIRCULAR_CONTEXT);

		CountingPostProcessor counter = new CountingPostProcessor();
		factory.addBeanPostProcessor(counter);

		BeanImpl1 impl1 = factory.getBean(BeanImpl1.class);
		assertNotNull(impl1);
		assertNotNull(impl1.getImpl2());
		assertNotNull(impl1.getImpl2());
		assertSame(impl1, impl1.getImpl2().getImpl1());
		assertEquals(1, counter.getCount("bean1"));
		assertEquals(1, counter.getCount("bean2"));
	}
