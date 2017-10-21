	@Test
	void executeTestsForPrimitiveArrayMethodInjectionCases() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(PrimitiveArrayMethodInjectionTestCase.class);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		UniqueId uniqueId = eventRecorder.getExecutionEvents().stream()
				.map(ExecutionEvent::getTestDescriptor)
				.distinct()
				.skip(2)
				.map(TestDescriptor::getUniqueId)
				.findFirst()
				.orElseThrow(AssertionError::new);
		// @formatter:on

		assertThat(UniqueId.parse(uniqueId.toString())).isEqualTo(uniqueId);
	}
