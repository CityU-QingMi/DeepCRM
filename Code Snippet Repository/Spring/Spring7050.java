	@Test
	public void pubSubDomainCustomForReply() {
		JmsMessageEndpointManager endpoint = new JmsMessageEndpointManager();
		JmsActivationSpecConfig config = new JmsActivationSpecConfig();
		config.setPubSubDomain(true);
		config.setReplyPubSubDomain(false);
		endpoint.setActivationSpecConfig(config);
		assertEquals(true, endpoint.isPubSubDomain());
		assertEquals(false, endpoint.isReplyPubSubDomain());
	}
