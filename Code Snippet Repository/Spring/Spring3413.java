	private void validateConditionMatch(Class<?>... classes) {
		long timestamp = System.currentTimeMillis();
		load(classes);
		TestEvent event = new TestEvent(this, "OK");

		ConditionalEventInterface listener = this.context.getBean(ConditionalEventInterface.class);
		this.eventCollector.assertNoEventReceived(listener);

		this.context.publishEvent(event);
		this.eventCollector.assertEvent(listener, event);
		this.eventCollector.assertTotalEventsCount(1);

		this.context.publishEvent("OK");
		this.eventCollector.assertEvent(listener, event, "OK");
		this.eventCollector.assertTotalEventsCount(2);

		this.context.publishEvent("NOT OK");
		this.eventCollector.assertTotalEventsCount(2);

		this.context.publishEvent(timestamp);
		this.eventCollector.assertEvent(listener, event, "OK", timestamp);
		this.eventCollector.assertTotalEventsCount(3);

		this.context.publishEvent(42d);
		this.eventCollector.assertEvent(listener, event, "OK", timestamp, 42d);
		this.eventCollector.assertTotalEventsCount(4);
	}
