	@Test
	public void afterCommitWithTransactionalComponentListenerProxiedViaDynamicProxy() {
		load(TransactionalConfiguration.class, TransactionalComponentTestListener.class);
		this.transactionTemplate.execute(status -> {
			getContext().publishEvent("SKIP");
			getEventCollector().assertNoEventReceived();
			return null;
		});
		getEventCollector().assertNoEventReceived();
	}
