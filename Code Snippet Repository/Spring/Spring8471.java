	@Test
	public void singletonLiteBean() {
		assertNotNull(injectedSingletonBean);
		assertTrue(injectedSingletonBean.isInitialized());

		LifecycleBean retrievedSingletonBean = applicationContext.getBean("singleton", LifecycleBean.class);
		assertNotNull(retrievedSingletonBean);
		assertTrue(retrievedSingletonBean.isInitialized());

		assertSame(injectedSingletonBean, retrievedSingletonBean);
	}
