	@Test
	public void publishBrokerAvailableEvent() {

		assertFalse(this.handler.isBrokerAvailable());
		assertEquals(Collections.emptyList(), this.handler.availabilityEvents);

		this.handler.publishBrokerAvailableEvent();

		assertTrue(this.handler.isBrokerAvailable());
		assertEquals(Arrays.asList(true), this.handler.availabilityEvents);
	}
