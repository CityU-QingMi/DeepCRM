	@Test
	public void dependencyStartedFirstButNotSmartLifecycle() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean beanMin = TestSmartLifecycleBean.forStartupTests(Integer.MIN_VALUE, startedBeans);
		TestSmartLifecycleBean bean7 = TestSmartLifecycleBean.forStartupTests(7, startedBeans);
		TestLifecycleBean simpleBean = TestLifecycleBean.forStartupTests(startedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("beanMin", beanMin);
		context.getBeanFactory().registerSingleton("bean7", bean7);
		context.getBeanFactory().registerSingleton("simpleBean", simpleBean);
		context.getBeanFactory().registerDependentBean("simpleBean", "beanMin");
		context.refresh();
		assertTrue(beanMin.isRunning());
		assertTrue(bean7.isRunning());
		assertTrue(simpleBean.isRunning());
		assertEquals(3, startedBeans.size());
		assertEquals(0, getPhase(startedBeans.get(0)));
		assertEquals(Integer.MIN_VALUE, getPhase(startedBeans.get(1)));
		assertEquals(7, getPhase(startedBeans.get(2)));
		context.stop();
	}
