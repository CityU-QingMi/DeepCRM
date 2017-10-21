	@Test
	void emptyReport() throws Exception {
		listener.testPlanExecutionStarted(testPlan);
		listener.testPlanExecutionFinished(testPlan);

		assertEquals(0, listener.getSummary().getTestsFailedCount());

		String summaryString = summaryAsString();
		assertAll("summary", //
			() -> assertTrue(summaryString.contains("Test run finished after"), "test run"), //

			() -> assertTrue(summaryString.contains("0 containers found"), "containers found"), //
			() -> assertTrue(summaryString.contains("0 containers skipped"), "containers skipped"), //
			() -> assertTrue(summaryString.contains("0 containers started"), "containers started"), //
			() -> assertTrue(summaryString.contains("0 containers aborted"), "containers aborted"), //
			() -> assertTrue(summaryString.contains("0 containers successful"), "containers successful"), //
			() -> assertTrue(summaryString.contains("0 containers failed"), "containers failed"), //

			() -> assertTrue(summaryString.contains("0 tests found"), "tests found"), //
			() -> assertTrue(summaryString.contains("0 tests skipped"), "tests skipped"), //
			() -> assertTrue(summaryString.contains("0 tests started"), "tests started"), //
			() -> assertTrue(summaryString.contains("0 tests aborted"), "tests aborted"), //
			() -> assertTrue(summaryString.contains("0 tests successful"), "tests successful"), //
			() -> assertTrue(summaryString.contains("0 tests failed"), "tests failed") //
		);

		assertEquals("", failuresAsString());
	}
