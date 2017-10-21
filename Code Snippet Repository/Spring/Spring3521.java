	@Test
	public void contextRefreshThenStartWithMixedBeans() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestLifecycleBean simpleBean1 = TestLifecycleBean.forStartupTests(startedBeans);
		TestLifecycleBean simpleBean2 = TestLifecycleBean.forStartupTests(startedBeans);
		TestSmartLifecycleBean smartBean1 = TestSmartLifecycleBean.forStartupTests(5, startedBeans);
		TestSmartLifecycleBean smartBean2 = TestSmartLifecycleBean.forStartupTests(-3, startedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("simpleBean1", simpleBean1);
		context.getBeanFactory().registerSingleton("smartBean1", smartBean1);
		context.getBeanFactory().registerSingleton("simpleBean2", simpleBean2);
		context.getBeanFactory().registerSingleton("smartBean2", smartBean2);
		assertFalse(simpleBean1.isRunning());
		assertFalse(simpleBean2.isRunning());
		assertFalse(smartBean1.isRunning());
		assertFalse(smartBean2.isRunning());
		context.refresh();
		assertTrue(smartBean1.isRunning());
		assertTrue(smartBean2.isRunning());
		assertFalse(simpleBean1.isRunning());
		assertFalse(simpleBean2.isRunning());
		assertEquals(2, startedBeans.size());
		assertEquals(-3, getPhase(startedBeans.get(0)));
		assertEquals(5, getPhase(startedBeans.get(1)));
		context.start();
		assertTrue(smartBean1.isRunning());
		assertTrue(smartBean2.isRunning());
		assertTrue(simpleBean1.isRunning());
		assertTrue(simpleBean2.isRunning());
		assertEquals(4, startedBeans.size());
		assertEquals(0, getPhase(startedBeans.get(2)));
		assertEquals(0, getPhase(startedBeans.get(3)));
	}
