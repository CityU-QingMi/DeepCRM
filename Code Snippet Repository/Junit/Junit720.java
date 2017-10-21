	@Override
	public void executionSkipped(TestIdentifier testIdentifier, String reason) {
		// @formatter:off
		long skippedContainers = concat(Stream.of(testIdentifier), testPlan.getDescendants(testIdentifier).stream())
				.filter(TestIdentifier::isContainer)
				.count();
		long skippedTests = concat(Stream.of(testIdentifier), testPlan.getDescendants(testIdentifier).stream())
				.filter(TestIdentifier::isTest)
				.count();
		// @formatter:on
		this.summary.containersSkipped.addAndGet(skippedContainers);
		this.summary.testsSkipped.addAndGet(skippedTests);
	}
