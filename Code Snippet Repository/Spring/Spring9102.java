	@Test
	public void immediately() {
		load(ImmediateTestListener.class);
		this.transactionTemplate.execute(status -> {
			getContext().publishEvent("test");
			getEventCollector().assertEvents(EventCollector.IMMEDIATELY, "test");
			getEventCollector().assertTotalEventsCount(1);
			return null;

		});
		getEventCollector().assertEvents(EventCollector.IMMEDIATELY, "test");
		getEventCollector().assertTotalEventsCount(1);
	}
