	@Test
	public void dependencyStartedFirstEvenIfItsPhaseIsHigher() throws Exception {
		CopyOnWriteArrayList<Lifecycle> startedBeans = new CopyOnWriteArrayList<>();
		TestSmartLifecycleBean beanMin = TestSmartLifecycleBean.forStartupTests(Integer.MIN_VALUE, startedBeans);
		TestSmartLifecycleBean bean2 = TestSmartLifecycleBean.forStartupTests(2, startedBeans);
		TestSmartLifecycleBean bean99 = TestSmartLifecycleBean.forStartupTests(99, startedBeans);
		TestSmartLifecycleBean beanMax = TestSmartLifecycleBean.forStartupTests(Integer.MAX_VALUE, startedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("beanMin", beanMin);
		context.getBeanFactory().registerSingleton("bean2", bean2);
		context.getBeanFactory().registerSingleton("bean99", bean99);
		context.getBeanFactory().registerSingleton("beanMax", beanMax);
		context.getBeanFactory().registerDependentBean("bean99", "bean2");
		context.refresh();
		assertTrue(beanMin.isRunning());
		assertTrue(bean2.isRunning());
		assertTrue(bean99.isRunning());
		assertTrue(beanMax.isRunning());
		assertEquals(4, startedBeans.size());
		assertEquals(Integer.MIN_VALUE, getPhase(startedBeans.get(0)));
		assertEquals(99, getPhase(startedBeans.get(1)));
		assertEquals(bean99, startedBeans.get(1));
		assertEquals(2, getPhase(startedBeans.get(2)));
		assertEquals(bean2, startedBeans.get(2));
		assertEquals(Integer.MAX_VALUE, getPhase(startedBeans.get(3)));
		context.stop();
	}
