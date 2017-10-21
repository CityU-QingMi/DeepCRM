	@Test
	public void factorySingleton() throws Exception {
		assertTrue(getBeanFactory().isSingleton("&singletonFactory"));
		assertTrue(getBeanFactory().isSingleton("singletonFactory"));
		TestBean tb = (TestBean) getBeanFactory().getBean("singletonFactory");
		assertTrue("Singleton from factory has correct name, not " + tb.getName(), tb.getName().equals(DummyFactory.SINGLETON_NAME));
		DummyFactory factory = (DummyFactory) getBeanFactory().getBean("&singletonFactory");
		TestBean tb2 = (TestBean) getBeanFactory().getBean("singletonFactory");
		assertTrue("Singleton references ==", tb == tb2);
		assertTrue("FactoryBean is BeanFactoryAware", factory.getBeanFactory() != null);
	}
