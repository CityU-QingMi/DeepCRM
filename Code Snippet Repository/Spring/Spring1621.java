	@Test
	public void constructorAutowireWithAutoSelfExclusion() throws Exception {
		DefaultListableBeanFactory beanFactory = getBeanFactory("autowire-constructor-with-exclusion.xml");
		TestBean rob = (TestBean) beanFactory.getBean("rob");
		TestBean sally = (TestBean) beanFactory.getBean("sally");
		assertEquals(sally, rob.getSpouse());
		TestBean rob2 = (TestBean) beanFactory.getBean("rob");
		assertEquals(rob, rob2);
		assertNotSame(rob, rob2);
		assertEquals(rob.getSpouse(), rob2.getSpouse());
		assertNotSame(rob.getSpouse(), rob2.getSpouse());
	}
