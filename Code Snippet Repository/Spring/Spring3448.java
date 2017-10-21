	@Test
	public void contextStartedEvent() {
		StaticApplicationContext context = new StaticApplicationContext();
		context.registerSingleton("lifecycle", LifecycleTestBean.class);
		context.registerSingleton("listener", LifecycleListener.class);
		context.refresh();
		LifecycleTestBean lifecycleBean = (LifecycleTestBean) context.getBean("lifecycle");
		LifecycleListener listener = (LifecycleListener) context.getBean("listener");
		assertFalse(lifecycleBean.isRunning());
		assertEquals(0, listener.getStartedCount());
		context.start();
		assertTrue(lifecycleBean.isRunning());
		assertEquals(1, listener.getStartedCount());
		assertSame(context, listener.getApplicationContext());
	}
