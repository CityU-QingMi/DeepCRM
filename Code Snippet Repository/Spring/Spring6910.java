	@Test
	public void testContainerConfiguration() throws Exception {
		Map<String, DefaultMessageListenerContainer> containers = context.getBeansOfType(DefaultMessageListenerContainer.class);
		ConnectionFactory defaultConnectionFactory = context.getBean(DEFAULT_CONNECTION_FACTORY, ConnectionFactory.class);
		ConnectionFactory explicitConnectionFactory = context.getBean(EXPLICIT_CONNECTION_FACTORY, ConnectionFactory.class);

		int defaultConnectionFactoryCount = 0;
		int explicitConnectionFactoryCount = 0;

		for (DefaultMessageListenerContainer container : containers.values()) {
			if (container.getConnectionFactory().equals(defaultConnectionFactory)) {
				defaultConnectionFactoryCount++;
			}
			else if (container.getConnectionFactory().equals(explicitConnectionFactory)) {
				explicitConnectionFactoryCount++;
			}
		}

		assertEquals("1 container should have the default connectionFactory", 1, defaultConnectionFactoryCount);
		assertEquals("2 containers should have the explicit connectionFactory", 2, explicitConnectionFactoryCount);
	}
