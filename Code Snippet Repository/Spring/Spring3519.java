	@Test
	public void singleSmartLifecycleAutoStartupWithNonAutoStartupDependency() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean bean = TestSmartLifecycleBean.forStartupTests(1, startedBeans);
		bean.setAutoStartup(true);
		TestSmartLifecycleBean dependency = TestSmartLifecycleBean.forStartupTests(1, startedBeans);
		dependency.setAutoStartup(false);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("bean", bean);
		context.getBeanFactory().registerSingleton("dependency", dependency);
		context.getBeanFactory().registerDependentBean("dependency", "bean");
		assertFalse(bean.isRunning());
		assertFalse(dependency.isRunning());
		context.refresh();
		assertTrue(bean.isRunning());
		assertFalse(dependency.isRunning());
		context.stop();
		assertFalse(bean.isRunning());
		assertFalse(dependency.isRunning());
		assertEquals(1, startedBeans.size());
	}
