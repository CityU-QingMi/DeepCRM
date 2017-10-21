	@Test
	public void testBeansCreated() {
		Map<String, ?> containers = context.getBeansOfType(DefaultMessageListenerContainer.class);
		assertEquals("Context should contain 3 JMS listener containers", 3, containers.size());

		containers = context.getBeansOfType(GenericMessageEndpointManager.class);
		assertEquals("Context should contain 3 JCA endpoint containers", 3, containers.size());

		Map<String, JmsListenerContainerFactory> containerFactories =
				context.getBeansOfType(JmsListenerContainerFactory.class);
		assertEquals("Context should contain 3 JmsListenerContainerFactory instances", 3, containerFactories.size());
	}
