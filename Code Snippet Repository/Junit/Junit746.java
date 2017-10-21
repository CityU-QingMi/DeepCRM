	@Test
	void notifiedWithCorrectNamesWhenMethodExecutionStarted() throws Exception {
		ArgumentCaptor<ReportEntry> entryCaptor = ArgumentCaptor.forClass(ReportEntry.class);

		TestPlan testPlan = TestPlan.from(Collections.singletonList(new EngineDescriptor(newId(), "Luke's Plan")));
		adapter.testPlanExecutionStarted(testPlan);

		TestIdentifier methodIdentifier = identifiersAsParentOnTestPlan(testPlan, newClassDescriptor(),
			newMethodDescriptor());

		adapter.executionStarted(methodIdentifier);
		verify(listener).testStarting(entryCaptor.capture());

		ReportEntry entry = entryCaptor.getValue();
		assertEquals(MY_TEST_METHOD_NAME + "()", entry.getName());
		assertEquals(MyTestClass.class.getName(), entry.getSourceName());
		assertNull(entry.getStackTraceWriter());
	}
