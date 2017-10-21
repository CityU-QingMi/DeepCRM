	@Test
	public void dependentShutdownFirstEvenIfItsPhaseIsLower() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		CopyOnWriteArrayList<Lifecycle> stoppedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean beanMin = TestSmartLifecycleBean.forShutdownTests(Integer.MIN_VALUE, 100, stoppedBeans);
		TestSmartLifecycleBean bean1 = TestSmartLifecycleBean.forShutdownTests(1, 200, stoppedBeans);
		TestSmartLifecycleBean bean99 = TestSmartLifecycleBean.forShutdownTests(99, 100, stoppedBeans);
		TestSmartLifecycleBean bean2 = TestSmartLifecycleBean.forShutdownTests(2, 300, stoppedBeans);
		TestSmartLifecycleBean bean7 = TestSmartLifecycleBean.forShutdownTests(7, 400, stoppedBeans);
		TestSmartLifecycleBean beanMax = TestSmartLifecycleBean.forShutdownTests(Integer.MAX_VALUE, 400, stoppedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("beanMin", beanMin);
		context.getBeanFactory().registerSingleton("bean1", bean1);
		context.getBeanFactory().registerSingleton("bean2", bean2);
		context.getBeanFactory().registerSingleton("bean7", bean7);
		context.getBeanFactory().registerSingleton("bean99", bean99);
		context.getBeanFactory().registerSingleton("beanMax", beanMax);
		context.getBeanFactory().registerDependentBean("bean99", "bean2");
		context.refresh();
		assertTrue(beanMin.isRunning());
		assertTrue(bean1.isRunning());
		assertTrue(bean2.isRunning());
		assertTrue(bean7.isRunning());
		assertTrue(bean99.isRunning());
		assertTrue(beanMax.isRunning());
		context.stop();
		assertFalse(beanMin.isRunning());
		assertFalse(bean1.isRunning());
		assertFalse(bean2.isRunning());
		assertFalse(bean7.isRunning());
		assertFalse(bean99.isRunning());
		assertFalse(beanMax.isRunning());
		assertEquals(6, stoppedBeans.size());
		assertEquals(Integer.MAX_VALUE, getPhase(stoppedBeans.get(0)));
		assertEquals(2, getPhase(stoppedBeans.get(1)));
		assertEquals(bean2, stoppedBeans.get(1));
		assertEquals(99, getPhase(stoppedBeans.get(2)));
		assertEquals(bean99, stoppedBeans.get(2));
		assertEquals(7, getPhase(stoppedBeans.get(3)));
		assertEquals(1, getPhase(stoppedBeans.get(4)));
		assertEquals(Integer.MIN_VALUE, getPhase(stoppedBeans.get(5)));
	}
