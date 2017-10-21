	@Test
	public void containerAreStartedByDefault() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				EnableJmsDefaultContainerFactoryConfig.class, DefaultBean.class);
		JmsListenerContainerTestFactory factory =
				context.getBean(JmsListenerContainerTestFactory.class);
		MessageListenerTestContainer container = factory.getListenerContainers().get(0);
		assertTrue(container.isAutoStartup());
		assertTrue(container.isStarted());
	}
