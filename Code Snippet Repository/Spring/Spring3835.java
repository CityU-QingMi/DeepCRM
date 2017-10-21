	@Test
	public void asyncMethodListener() throws Exception {
		originalThreadName = Thread.currentThread().getName();
		listenerCalled = 0;
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("asyncTest", new RootBeanDefinition(AsyncMethodListener.class));
		context.registerBeanDefinition("autoProxyCreator", new RootBeanDefinition(DefaultAdvisorAutoProxyCreator.class));
		context.registerBeanDefinition("asyncAdvisor", new RootBeanDefinition(AsyncAnnotationAdvisor.class));
		context.refresh();
		Thread.sleep(1000);
		assertEquals(1, listenerCalled);
	}
