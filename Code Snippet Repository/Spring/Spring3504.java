	@Test
	public void smartLifecycleGroupShutdown() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		CopyOnWriteArrayList<Lifecycle> stoppedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean bean1 = TestSmartLifecycleBean.forShutdownTests(1, 300, stoppedBeans);
		TestSmartLifecycleBean bean2 = TestSmartLifecycleBean.forShutdownTests(3, 100, stoppedBeans);
		TestSmartLifecycleBean bean3 = TestSmartLifecycleBean.forShutdownTests(1, 600, stoppedBeans);
		TestSmartLifecycleBean bean4 = TestSmartLifecycleBean.forShutdownTests(2, 400, stoppedBeans);
		TestSmartLifecycleBean bean5 = TestSmartLifecycleBean.forShutdownTests(2, 700, stoppedBeans);
		TestSmartLifecycleBean bean6 = TestSmartLifecycleBean.forShutdownTests(Integer.MAX_VALUE, 200, stoppedBeans);
		TestSmartLifecycleBean bean7 = TestSmartLifecycleBean.forShutdownTests(3, 200, stoppedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("bean1", bean1);
		context.getBeanFactory().registerSingleton("bean2", bean2);
		context.getBeanFactory().registerSingleton("bean3", bean3);
		context.getBeanFactory().registerSingleton("bean4", bean4);
		context.getBeanFactory().registerSingleton("bean5", bean5);
		context.getBeanFactory().registerSingleton("bean6", bean6);
		context.getBeanFactory().registerSingleton("bean7", bean7);
		context.refresh();
		context.stop();
		assertEquals(Integer.MAX_VALUE, getPhase(stoppedBeans.get(0)));
		assertEquals(3, getPhase(stoppedBeans.get(1)));
		assertEquals(3, getPhase(stoppedBeans.get(2)));
		assertEquals(2, getPhase(stoppedBeans.get(3)));
		assertEquals(2, getPhase(stoppedBeans.get(4)));
		assertEquals(1, getPhase(stoppedBeans.get(5)));
		assertEquals(1, getPhase(stoppedBeans.get(6)));
	}
