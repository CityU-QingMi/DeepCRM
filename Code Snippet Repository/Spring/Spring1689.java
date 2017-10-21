	@Test
	public void beanPostProcessor() throws Exception {
		TestBean kerry = (TestBean) getBeanFactory().getBean("kerry");
		TestBean kathy = (TestBean) getBeanFactory().getBean("kathy");
		DummyFactory factory = (DummyFactory) getBeanFactory().getBean("&singletonFactory");
		TestBean factoryCreated = (TestBean) getBeanFactory().getBean("singletonFactory");
		assertTrue(kerry.isPostProcessed());
		assertTrue(kathy.isPostProcessed());
		assertTrue(factory.isPostProcessed());
		assertTrue(factoryCreated.isPostProcessed());
	}
