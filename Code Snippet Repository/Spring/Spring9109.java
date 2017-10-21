	@Test
	public void immediatelyImpactsCurrentTransaction() {
		load(ImmediateTestListener.class, BeforeCommitTestListener.class);
		try {
			this.transactionTemplate.execute(status -> {
				getContext().publishEvent("FAIL");
				fail("Should have thrown an exception at this point");
				return null;
			});
		}
		catch (IllegalStateException e) {
			assertTrue(e.getMessage().contains("Test exception"));
			assertTrue(e.getMessage().contains(EventCollector.IMMEDIATELY));
		}
		getEventCollector().assertEvents(EventCollector.IMMEDIATELY, "FAIL");
		getEventCollector().assertTotalEventsCount(1);
	}
