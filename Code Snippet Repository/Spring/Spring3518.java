	@Test
	public void singleSmartLifecycleWithoutAutoStartup() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean bean = TestSmartLifecycleBean.forStartupTests(1, startedBeans);
		bean.setAutoStartup(false);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("bean", bean);
		assertFalse(bean.isRunning());
		context.refresh();
		assertFalse(bean.isRunning());
		assertEquals(0, startedBeans.size());
		context.start();
		assertTrue(bean.isRunning());
		assertEquals(1, startedBeans.size());
		context.stop();
	}
