	@Test
	public void testBeanDefinitionRegistryPostProcessor() throws Exception {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerSingleton("tb1", TestBean.class);
		ac.registerSingleton("tb2", TestBean.class);
		TestBeanDefinitionRegistryPostProcessor bdrpp = new TestBeanDefinitionRegistryPostProcessor();
		ac.addBeanFactoryPostProcessor(bdrpp);
		assertFalse(bdrpp.wasCalled);
		ac.refresh();
		assertTrue(bdrpp.wasCalled);
		assertTrue(ac.getBean(TestBeanFactoryPostProcessor.class).wasCalled);
	}
