	@Test
	public void containerCanBeStarterViaTheRegistry() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				EnableJmsAutoStartupFalseConfig.class, DefaultBean.class);
		JmsListenerContainerTestFactory factory =
				context.getBean(JmsListenerContainerTestFactory.class);
		MessageListenerTestContainer container = factory.getListenerContainers().get(0);
		assertFalse(container.isAutoStartup());
		assertFalse(container.isStarted());
		JmsListenerEndpointRegistry registry = context.getBean(JmsListenerEndpointRegistry.class);
		registry.start();
		assertTrue(container.isStarted());
	}
