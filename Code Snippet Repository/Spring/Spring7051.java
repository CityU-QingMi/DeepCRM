	@Test
	public void customReplyQosSettings() {
		JmsMessageEndpointManager endpoint = new JmsMessageEndpointManager();
		JmsActivationSpecConfig config = new JmsActivationSpecConfig();
		QosSettings settings = new QosSettings(1, 3, 5);
		config.setReplyQosSettings(settings);
		endpoint.setActivationSpecConfig(config);
		assertNotNull(endpoint.getReplyQosSettings());
		assertEquals(1, endpoint.getReplyQosSettings().getDeliveryMode());
		assertEquals(3, endpoint.getReplyQosSettings().getPriority());
		assertEquals(5, endpoint.getReplyQosSettings().getTimeToLive());
	}
