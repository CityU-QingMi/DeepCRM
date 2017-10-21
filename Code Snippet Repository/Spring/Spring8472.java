	@Test
	public void prototypeLiteBean() {
		assertNotNull(injectedPrototypeBean);
		assertTrue(injectedPrototypeBean.isInitialized());

		LifecycleBean retrievedPrototypeBean = applicationContext.getBean("prototype", LifecycleBean.class);
		assertNotNull(retrievedPrototypeBean);
		assertTrue(retrievedPrototypeBean.isInitialized());

		assertNotSame(injectedPrototypeBean, retrievedPrototypeBean);
	}
