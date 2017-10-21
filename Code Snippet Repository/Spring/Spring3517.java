	@Test
	public void singleSmartLifecycleAutoStartupWithLazyInitFactoryBean() throws Exception {
		StaticApplicationContext context = new StaticApplicationContext();
		RootBeanDefinition bd = new RootBeanDefinition(DummySmartLifecycleFactoryBean.class);
		bd.setLazyInit(true);
		context.registerBeanDefinition("bean", bd);
		context.refresh();
		DummySmartLifecycleFactoryBean bean = context.getBean("&bean", DummySmartLifecycleFactoryBean.class);
		assertTrue(bean.isRunning());
		context.stop();
		assertFalse(bean.isRunning());
	}
