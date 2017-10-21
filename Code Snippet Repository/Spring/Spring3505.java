	@Test
	public void singleSmartLifecycleShutdown() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		CopyOnWriteArrayList<Lifecycle> stoppedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean bean = TestSmartLifecycleBean.forShutdownTests(99, 300, stoppedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("bean", bean);
		context.refresh();
		assertTrue(bean.isRunning());
		context.stop();
		assertEquals(1, stoppedBeans.size());
		assertFalse(bean.isRunning());
		assertEquals(bean, stoppedBeans.get(0));
	}
