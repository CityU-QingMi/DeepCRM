	@Test
	public void afterRollback() {
		load(AfterCompletionExplicitTestListener.class);
		this.transactionTemplate.execute(status -> {
			getContext().publishEvent("test");
			getEventCollector().assertNoEventReceived();
			status.setRollbackOnly();
			return null;
		});
		getEventCollector().assertEvents(EventCollector.AFTER_ROLLBACK, "test");
		getEventCollector().assertTotalEventsCount(1); // After commit not invoked
	}
