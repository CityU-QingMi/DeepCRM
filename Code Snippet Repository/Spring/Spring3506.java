	@Test
	public void singleLifecycleShutdown() throws Exception {
		CopyOnWriteArrayList<Lifecycle> stoppedBeans = new CopyOnWriteArrayList<>();
		Lifecycle bean = new TestLifecycleBean(null, stoppedBeans);
		StaticApplicationContext context = new StaticApplicationContext();
		context.getBeanFactory().registerSingleton("bean", bean);
		context.refresh();
		assertFalse(bean.isRunning());
		bean.start();
		assertTrue(bean.isRunning());
		context.stop();
		assertEquals(1, stoppedBeans.size());
		assertFalse(bean.isRunning());
		assertEquals(bean, stoppedBeans.get(0));
	}
