	@Test
	public void simpleMessageListener() throws Exception {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class, SimpleMessageListenerTestBean.class);

		JmsListenerContainerTestFactory factory = context.getBean(JmsListenerContainerTestFactory.class);
		assertEquals("One container should have been registered", 1, factory.getListenerContainers().size());
		MessageListenerTestContainer container = factory.getListenerContainers().get(0);

		JmsListenerEndpoint endpoint = container.getEndpoint();
		assertEquals("Wrong endpoint type", MethodJmsListenerEndpoint.class, endpoint.getClass());
		MethodJmsListenerEndpoint methodEndpoint = (MethodJmsListenerEndpoint) endpoint;
		assertEquals(SimpleMessageListenerTestBean.class, methodEndpoint.getBean().getClass());
		assertEquals(SimpleMessageListenerTestBean.class.getMethod("handleIt", String.class), methodEndpoint.getMethod());
		assertEquals(SimpleMessageListenerTestBean.class.getMethod("handleIt", String.class), methodEndpoint.getMostSpecificMethod());

		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		methodEndpoint.setupListenerContainer(listenerContainer);
		assertNotNull(listenerContainer.getMessageListener());

		assertTrue("Should have been started " + container, container.isStarted());
		context.close(); // Close and stop the listeners
		assertTrue("Should have been stopped " + container, container.isStopped());
	}
