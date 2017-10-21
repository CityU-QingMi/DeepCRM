	@Test
	public void composedJmsListeners() {
		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
			EnableJmsDefaultContainerFactoryConfig.class, ComposedJmsListenersBean.class)) {
			JmsListenerContainerTestFactory simpleFactory = context.getBean("jmsListenerContainerFactory",
				JmsListenerContainerTestFactory.class);
			assertEquals(2, simpleFactory.getListenerContainers().size());

			MethodJmsListenerEndpoint first = (MethodJmsListenerEndpoint) simpleFactory.getListenerContainer(
				"first").getEndpoint();
			assertEquals("first", first.getId());
			assertEquals("orderQueue", first.getDestination());
			assertNull(first.getConcurrency());

			MethodJmsListenerEndpoint second = (MethodJmsListenerEndpoint) simpleFactory.getListenerContainer(
				"second").getEndpoint();
			assertEquals("second", second.getId());
			assertEquals("billingQueue", second.getDestination());
			assertEquals("2-10", second.getConcurrency());
		}
	}
