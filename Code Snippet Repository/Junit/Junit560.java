	@Test
	void enableRuleMigrationSupportAnnotationWorksForBothRuleTypes() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(
			EnableRuleMigrationSupportWithBothRuleTypesTestCase.class);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed");

		assertEquals(true, EnableRuleMigrationSupportWithBothRuleTypesTestCase.afterOfRule1WasExecuted,
			"after of rule 1 executed?");
		assertEquals(true, EnableRuleMigrationSupportWithBothRuleTypesTestCase.beforeOfRule2WasExecuted,
			"before of rule 2 executed?");
		assertEquals(true, EnableRuleMigrationSupportWithBothRuleTypesTestCase.afterOfRule2WasExecuted,
			"before of rule 2 executed?");
	}
