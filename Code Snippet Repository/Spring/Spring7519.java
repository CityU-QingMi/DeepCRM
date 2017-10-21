	@Test
	public void publishBrokerUnavailableEvent() {

		this.handler.publishBrokerAvailableEvent();
		assertTrue(this.handler.isBrokerAvailable());

		this.handler.publishBrokerUnavailableEvent();
		assertFalse(this.handler.isBrokerAvailable());

		assertEquals(Arrays.asList(true, false), this.handler.availabilityEvents);
	}
