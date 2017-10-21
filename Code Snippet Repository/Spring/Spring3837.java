	@Test
	public void asyncPrototypeClassListener() throws Exception {
		originalThreadName = Thread.currentThread().getName();
		listenerCalled = 0;
		listenerConstructed = 0;
		GenericApplicationContext context = new GenericApplicationContext();
		RootBeanDefinition listenerDef = new RootBeanDefinition(AsyncClassListener.class);
		listenerDef.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		context.registerBeanDefinition("asyncTest", listenerDef);
		context.registerBeanDefinition("autoProxyCreator", new RootBeanDefinition(DefaultAdvisorAutoProxyCreator.class));
		context.registerBeanDefinition("asyncAdvisor", new RootBeanDefinition(AsyncAnnotationAdvisor.class));
		context.refresh();
		context.close();
		Thread.sleep(1000);
		assertEquals(2, listenerCalled);
		assertEquals(2, listenerConstructed);
	}
