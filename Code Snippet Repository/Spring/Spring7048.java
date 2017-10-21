	@Test
	public void activeMQResourceAdapterSetup() {
		activationSpecConfig.setAcknowledgeMode(Session.SESSION_TRANSACTED);
		JmsActivationSpecFactory activationSpecFactory = new DefaultJmsActivationSpecFactory();
		StubActiveMQActivationSpec spec = (StubActiveMQActivationSpec) activationSpecFactory.createActivationSpec(
				new StubActiveMQResourceAdapter(), activationSpecConfig);

		assertEquals(5, spec.getMaxSessions());
		assertEquals(3, spec.getMaxMessagesPerSessions());
		assertTrue(spec.isUseRAManagedTransaction());
	}
