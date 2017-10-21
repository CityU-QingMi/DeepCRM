	@Test
	public void singleSmartLifecycleAutoStartupWithLazyInit() throws Exception {
		StaticApplicationContext context = new StaticApplicationContext();
		RootBeanDefinition bd = new RootBeanDefinition(DummySmartLifecycleBean.class);
		bd.setLazyInit(true);
		context.registerBeanDefinition("bean", bd);
		context.refresh();
		DummySmartLifecycleBean bean = context.getBean("bean", DummySmartLifecycleBean.class);
		assertTrue(bean.isRunning());
		context.stop();
		assertFalse(bean.isRunning());
	}
