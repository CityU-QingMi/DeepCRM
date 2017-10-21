	@Test
	public void singleSmartLifecycleAutoStartup() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean bean = TestSmartLifecycleBean.forStartupTests(1, startedBeans);
		bean.setAutoStartup(true);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("bean", bean);
		assertFalse(bean.isRunning());
		context.refresh();
		assertTrue(bean.isRunning());
		context.stop();
		assertFalse(bean.isRunning());
		assertEquals(1, startedBeans.size());
	}
