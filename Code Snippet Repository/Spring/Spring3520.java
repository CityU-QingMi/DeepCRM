	@Test
	public void smartLifecycleGroupStartup() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean beanMin = TestSmartLifecycleBean.forStartupTests(Integer.MIN_VALUE, startedBeans);
		TestSmartLifecycleBean bean1 = TestSmartLifecycleBean.forStartupTests(1, startedBeans);
		TestSmartLifecycleBean bean2 = TestSmartLifecycleBean.forStartupTests(2, startedBeans);
		TestSmartLifecycleBean bean3 = TestSmartLifecycleBean.forStartupTests(3, startedBeans);
		TestSmartLifecycleBean beanMax = TestSmartLifecycleBean.forStartupTests(Integer.MAX_VALUE, startedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("bean3", bean3);
		context.getBeanFactory().registerSingleton("beanMin", beanMin);
		context.getBeanFactory().registerSingleton("bean2", bean2);
		context.getBeanFactory().registerSingleton("beanMax", beanMax);
		context.getBeanFactory().registerSingleton("bean1", bean1);
		assertFalse(beanMin.isRunning());
		assertFalse(bean1.isRunning());
		assertFalse(bean2.isRunning());
		assertFalse(bean3.isRunning());
		assertFalse(beanMax.isRunning());
		context.refresh();
		assertTrue(beanMin.isRunning());
		assertTrue(bean1.isRunning());
		assertTrue(bean2.isRunning());
		assertTrue(bean3.isRunning());
		assertTrue(beanMax.isRunning());
		context.stop();
		assertEquals(5, startedBeans.size());
		assertEquals(Integer.MIN_VALUE, getPhase(startedBeans.get(0)));
		assertEquals(1, getPhase(startedBeans.get(1)));
		assertEquals(2, getPhase(startedBeans.get(2)));
		assertEquals(3, getPhase(startedBeans.get(3)));
		assertEquals(Integer.MAX_VALUE, getPhase(startedBeans.get(4)));
	}
