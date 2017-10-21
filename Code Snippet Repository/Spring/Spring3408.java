	@Test
	public void listenerWithNonMatchingPayload() {
		load(TestEventListener.class);
		TestEventListener listener = this.context.getBean(TestEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		this.context.publishEvent(123L);
		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertTotalEventsCount(0);
	}
