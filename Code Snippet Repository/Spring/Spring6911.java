	@Test
	public void testJcaContainerConfiguration() throws Exception {
		Map<String, JmsMessageEndpointManager> containers = context.getBeansOfType(JmsMessageEndpointManager.class);

		assertTrue("listener3 not found", containers.containsKey("listener3"));
		JmsMessageEndpointManager listener3 = containers.get("listener3");
		assertEquals("Wrong resource adapter",
				context.getBean("testResourceAdapter"), listener3.getResourceAdapter());
		assertEquals("Wrong activation spec factory", context.getBean("testActivationSpecFactory"),
				new DirectFieldAccessor(listener3).getPropertyValue("activationSpecFactory"));


		Object endpointFactory = new DirectFieldAccessor(listener3).getPropertyValue("endpointFactory");
		Object messageListener = new DirectFieldAccessor(endpointFactory).getPropertyValue("messageListener");
		assertEquals("Wrong message listener", MessageListenerAdapter.class, messageListener.getClass());
		MessageListenerAdapter adapter = (MessageListenerAdapter) messageListener;
		DirectFieldAccessor adapterFieldAccessor = new DirectFieldAccessor(adapter);
		assertEquals("Message converter not set properly", context.getBean("testMessageConverter"),
				adapterFieldAccessor.getPropertyValue("messageConverter"));
		assertEquals("Wrong delegate", context.getBean("testBean1"),
				adapterFieldAccessor.getPropertyValue("delegate"));
		assertEquals("Wrong method name", "setName",
				adapterFieldAccessor.getPropertyValue("defaultListenerMethod"));
	}
