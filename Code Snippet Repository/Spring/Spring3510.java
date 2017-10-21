	@Test
	public void dependencyStartedFirstAndIsSmartLifecycle() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean beanNegative = TestSmartLifecycleBean.forStartupTests(-99, startedBeans);
		TestSmartLifecycleBean bean99 = TestSmartLifecycleBean.forStartupTests(99, startedBeans);
		TestSmartLifecycleBean bean7 = TestSmartLifecycleBean.forStartupTests(7, startedBeans);
		TestLifecycleBean simpleBean = TestLifecycleBean.forStartupTests(startedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("beanNegative", beanNegative);
		context.getBeanFactory().registerSingleton("bean7", bean7);
		context.getBeanFactory().registerSingleton("bean99", bean99);
		context.getBeanFactory().registerSingleton("simpleBean", simpleBean);
		context.getBeanFactory().registerDependentBean("bean7", "simpleBean");
		context.refresh();
		context.stop();
		startedBeans.clear();
		// clean start so that simpleBean is included
		context.start();
		assertTrue(beanNegative.isRunning());
		assertTrue(bean99.isRunning());
		assertTrue(bean7.isRunning());
		assertTrue(simpleBean.isRunning());
		assertEquals(4, startedBeans.size());
		assertEquals(-99, getPhase(startedBeans.get(0)));
		assertEquals(7, getPhase(startedBeans.get(1)));
		assertEquals(0, getPhase(startedBeans.get(2)));
		assertEquals(99, getPhase(startedBeans.get(3)));
		context.stop();
	}
