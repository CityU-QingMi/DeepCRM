	public void testJmsListenerRepeatable(ApplicationContext context) {
		JmsListenerContainerTestFactory simpleFactory =
				context.getBean("jmsListenerContainerFactory", JmsListenerContainerTestFactory.class);
		assertEquals(2, simpleFactory.getListenerContainers().size());

		MethodJmsListenerEndpoint first = (MethodJmsListenerEndpoint)
				simpleFactory.getListenerContainer("first").getEndpoint();
		assertEquals("first", first.getId());
		assertEquals("myQueue", first.getDestination());
		assertEquals(null, first.getConcurrency());

		MethodJmsListenerEndpoint second = (MethodJmsListenerEndpoint)
				simpleFactory.getListenerContainer("second").getEndpoint();
		assertEquals("second", second.getId());
		assertEquals("anotherQueue", second.getDestination());
		assertEquals("2-10", second.getConcurrency());
	}
