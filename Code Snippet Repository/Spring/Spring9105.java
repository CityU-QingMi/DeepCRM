	@Test
	public void conditionFoundOnTransactionalEventListener() {
		load(ImmediateTestListener.class);
		this.transactionTemplate.execute(status -> {
			getContext().publishEvent("SKIP");
			getEventCollector().assertNoEventReceived();
			return null;
		});
		getEventCollector().assertNoEventReceived();
	}
