	@Test
	void notifiedWithCorrectNamesWhenClassExecutionFailed() throws Exception {
		ArgumentCaptor<ReportEntry> entryCaptor = ArgumentCaptor.forClass(ReportEntry.class);
		TestPlan testPlan = TestPlan.from(Collections.singletonList(new EngineDescriptor(newId(), "Luke's Plan")));
		adapter.testPlanExecutionStarted(testPlan);

		adapter.executionFinished(identifiersAsParentOnTestPlan(testPlan, newEngineDescriptor(), newClassDescriptor()),
			TestExecutionResult.failed(new AssertionError()));
		verify(listener).testFailed(entryCaptor.capture());

		ReportEntry entry = entryCaptor.getValue();
		assertEquals("engine", entry.getSourceName());
		assertNotNull(entry.getStackTraceWriter());
	}
