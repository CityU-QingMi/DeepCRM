	@Test
	void allTestsInClassAreRunWithAfterEach() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(MyStandardTestCase.class);

		assertEquals(4, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(4, MyStandardTestCase.countAfter, "# after each calls");

		assertEquals(2, eventRecorder.getContainerStartedCount(), "# containers started");
		assertEquals(2, eventRecorder.getContainerFinishedCount(), "# containers finished");
	}
