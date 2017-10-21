	@Test
	public void listenerWithSimplePayload() {
		load(TestEventListener.class);
		TestEventListener listener = this.context.getBean(TestEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		this.context.publishEvent("test");
		this.eventCollector.assertEvent(listener, "test");
		this.eventCollector.assertTotalEventsCount(1);
	}
