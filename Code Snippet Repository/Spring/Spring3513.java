	@Test
	public void dependentShutdownFirstButNotSmartLifecycle() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		CopyOnWriteArrayList<Lifecycle> stoppedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean bean1 = TestSmartLifecycleBean.forShutdownTests(1, 200, stoppedBeans);
		TestLifecycleBean simpleBean = TestLifecycleBean.forShutdownTests(stoppedBeans);
		TestSmartLifecycleBean bean2 = TestSmartLifecycleBean.forShutdownTests(2, 300, stoppedBeans);
		TestSmartLifecycleBean bean7 = TestSmartLifecycleBean.forShutdownTests(7, 400, stoppedBeans);
		TestSmartLifecycleBean beanMin = TestSmartLifecycleBean.forShutdownTests(Integer.MIN_VALUE, 400, stoppedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("beanMin", beanMin);
		context.getBeanFactory().registerSingleton("bean1", bean1);
		context.getBeanFactory().registerSingleton("bean2", bean2);
		context.getBeanFactory().registerSingleton("bean7", bean7);
		context.getBeanFactory().registerSingleton("simpleBean", simpleBean);
		context.getBeanFactory().registerDependentBean("bean2", "simpleBean");
		context.refresh();
		assertTrue(beanMin.isRunning());
		assertTrue(bean1.isRunning());
		assertTrue(bean2.isRunning());
		assertTrue(bean7.isRunning());
		assertFalse(simpleBean.isRunning());
		simpleBean.start();
		assertTrue(simpleBean.isRunning());
		context.stop();
		assertFalse(beanMin.isRunning());
		assertFalse(bean1.isRunning());
		assertFalse(bean2.isRunning());
		assertFalse(bean7.isRunning());
		assertFalse(simpleBean.isRunning());
		assertEquals(5, stoppedBeans.size());
		assertEquals(7, getPhase(stoppedBeans.get(0)));
		assertEquals(0, getPhase(stoppedBeans.get(1)));
		assertEquals(2, getPhase(stoppedBeans.get(2)));
		assertEquals(1, getPhase(stoppedBeans.get(3)));
		assertEquals(Integer.MIN_VALUE, getPhase(stoppedBeans.get(4)));
	}
