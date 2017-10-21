	@Test
	void notifiedWithCorrectNamesWhenClassExecutionSkipped() throws Exception {
		ArgumentCaptor<ReportEntry> entryCaptor = ArgumentCaptor.forClass(ReportEntry.class);
		TestPlan testPlan = TestPlan.from(Collections.singletonList(new EngineDescriptor(newId(), "Luke's Plan")));
		adapter.testPlanExecutionStarted(testPlan);

		TestIdentifier classIdentifier = identifiersAsParentOnTestPlan(testPlan, newEngineDescriptor(),
			newClassDescriptor());

		adapter.executionSkipped(classIdentifier, "test");
		verify(listener).testSkipped(entryCaptor.capture());

		ReportEntry entry = entryCaptor.getValue();
		assertTrue(MyTestClass.class.getTypeName().contains(entry.getName()));
		assertEquals("engine", entry.getSourceName());
	}
