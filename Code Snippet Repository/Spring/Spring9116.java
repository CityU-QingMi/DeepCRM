	@Test
	public void beforeCommitWithException() { // Validates the custom synchronization is invoked
		load(BeforeCommitTestListener.class);
		try {
			this.transactionTemplate.execute(status -> {
				TransactionSynchronizationManager.registerSynchronization(new EventTransactionSynchronization(10) {
					@Override
					public void beforeCommit(boolean readOnly) {
						throw new IllegalStateException("test");
					}
				});
				getContext().publishEvent("test");
				getEventCollector().assertNoEventReceived();
				return null;

			});
			fail("Should have thrown an exception");
		}
		catch (IllegalStateException e) {
			// Test exception - ignore
		}
		getEventCollector().assertNoEventReceived(); // Before commit not invoked
	}
