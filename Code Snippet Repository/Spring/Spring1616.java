	@Test
	public void byTypeAutowireWithAutoSelfExclusion() throws Exception {
		CountingFactory.reset();
		DefaultListableBeanFactory beanFactory = getBeanFactory("autowire-with-exclusion.xml");
		beanFactory.preInstantiateSingletons();
		TestBean rob = (TestBean) beanFactory.getBean("rob");
		TestBean sally = (TestBean) beanFactory.getBean("sally");
		assertEquals(sally, rob.getSpouse());
		assertEquals(1, CountingFactory.getFactoryBeanInstanceCount());
	}
