	public void testFullConfiguration(ApplicationContext context) {
		JmsListenerContainerTestFactory simpleFactory =
				context.getBean("simpleFactory", JmsListenerContainerTestFactory.class);
		assertEquals(1, simpleFactory.getListenerContainers().size());
		MethodJmsListenerEndpoint endpoint = (MethodJmsListenerEndpoint)
				simpleFactory.getListenerContainers().get(0).getEndpoint();
		assertEquals("listener1", endpoint.getId());
		assertEquals("queueIn", endpoint.getDestination());
		assertEquals("mySelector", endpoint.getSelector());
		assertEquals("mySubscription", endpoint.getSubscription());
		assertEquals("1-10", endpoint.getConcurrency());

		Method m = ReflectionUtils.findMethod(endpoint.getClass(), "getDefaultResponseDestination");
		ReflectionUtils.makeAccessible(m);
		Object destination = ReflectionUtils.invokeMethod(m, endpoint);
		assertEquals("queueOut", destination);
	}
