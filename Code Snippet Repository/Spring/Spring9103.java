	@Test
	public void regularTransaction() {
		load(ImmediateTestListener.class, BeforeCommitTestListener.class, AfterCompletionExplicitTestListener.class);
		this.transactionTemplate.execute(status -> {
			TransactionSynchronizationManager.registerSynchronization(new EventTransactionSynchronization(10) {
				@Override
				public void beforeCommit(boolean readOnly) {
					getEventCollector().assertTotalEventsCount(1); // Immediate event
					getEventCollector().assertEvents(EventCollector.IMMEDIATELY, "test");
				}
			});
			TransactionSynchronizationManager.registerSynchronization(new EventTransactionSynchronization(20) {
				@Override
				public void beforeCommit(boolean readOnly) {
					getEventCollector().assertEvents(EventCollector.BEFORE_COMMIT, "test");
					getEventCollector().assertTotalEventsCount(2);
				}
			});
			getContext().publishEvent("test");
			getEventCollector().assertTotalEventsCount(1);
			return null;

		});
		getEventCollector().assertEvents(EventCollector.AFTER_COMMIT, "test");
		getEventCollector().assertTotalEventsCount(3); // Immediate, before commit, after commit
	}
