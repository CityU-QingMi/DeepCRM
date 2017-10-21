	@Test
	void notifiedWithParentDisplayNameWhenTestClassUnknown() throws Exception {
		// Set up a test plan
		TestPlan plan = TestPlan.from(Collections.singletonList(new EngineDescriptor(newId(), "Luke's Plan")));
		adapter.testPlanExecutionStarted(plan);

		// Use the test plan to set up child with parent.
		final String parentDisplay = "I am your father";
		TestIdentifier child = newSourcelessIdentifierWithParent(plan, parentDisplay);
		adapter.executionStarted(child);

		// Check that the adapter has informed Surefire that the test has been invoked,
		// with the parent name as source (since the test case itself had no source).
		ArgumentCaptor<ReportEntry> entryCaptor = ArgumentCaptor.forClass(ReportEntry.class);
		verify(listener).testStarting(entryCaptor.capture());
		assertEquals(parentDisplay, entryCaptor.getValue().getSourceName());
	}
