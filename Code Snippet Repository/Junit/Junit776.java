	@Test
	void selectingWholeParameterizedClassRunsTestsWithAllValues() {
		executeTests(selectClass(JUnit4ParameterizedTestCase.class));

		Map<TestExecutionResult.Status, Integer> expectedCallCounts = new HashMap<>();
		expectedCallCounts.put(SUCCESSFUL, 3);
		expectedCallCounts.put(FAILED, 9);

		assertEquals(expectedCallCounts, callCounts);
	}
