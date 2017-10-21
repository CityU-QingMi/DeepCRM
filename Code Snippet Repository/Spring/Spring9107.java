	@Test
	public void conditionFoundOnMetaAnnotation() {
		load(AfterCommitMetaAnnotationTestListener.class);
		this.transactionTemplate.execute(status -> {
			getContext().publishEvent("SKIP");
			getEventCollector().assertNoEventReceived();
			return null;

		});
		getEventCollector().assertNoEventReceived();
	}
