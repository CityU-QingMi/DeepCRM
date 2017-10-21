	@Test
	public void listenerWithGenericApplicationEvent() {
		load(GenericEventListener.class);
		GenericEventListener listener = this.context.getBean(GenericEventListener.class);

		this.eventCollector.assertNoEventReceived(listener);
		this.context.publishEvent("TEST");
		this.eventCollector.assertEvent(listener, "TEST");
		this.eventCollector.assertTotalEventsCount(1);
	}
