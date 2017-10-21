	@Test
	public void conditionDoesNotMatch() {
		long maxLong = Long.MAX_VALUE;
		load(ConditionalEventListener.class);
		TestEvent event = new TestEvent(this, "KO");
		TestEventListener listener = this.context.getBean(ConditionalEventListener.class);
		this.eventCollector.assertNoEventReceived(listener);

		this.context.publishEvent(event);
		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertTotalEventsCount(0);

		this.context.publishEvent("KO");
		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertTotalEventsCount(0);

		this.context.publishEvent(maxLong);
		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertTotalEventsCount(0);

		this.context.publishEvent(24d);
		this.eventCollector.assertNoEventReceived(listener);
		this.eventCollector.assertTotalEventsCount(0);
	}
