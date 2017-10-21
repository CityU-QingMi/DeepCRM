	private TestDescriptor findTestDescriptor(ExecutionEventRecorder eventRecorder,
			Condition<ExecutionEvent> condition) {
		// @formatter:off
		return eventRecorder.eventStream()
				.filter(condition::matches)
				.findAny()
				.map(ExecutionEvent::getTestDescriptor)
				.orElseThrow(() -> new AssertionFailedError("Could not find execution event for condition: " + condition));
		// @formatter:on
	}
