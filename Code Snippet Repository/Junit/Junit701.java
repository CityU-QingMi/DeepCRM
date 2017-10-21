	@SafeVarargs
	public static void assertRecordedExecutionEventsContainsExactly(List<ExecutionEvent> executionEvents,
			Condition<? super ExecutionEvent>... conditions) {
		SoftAssertions softly = new SoftAssertions();
		assertThat(executionEvents).hasSize(conditions.length);
		for (int i = 0; i < conditions.length; i++) {
			softly.assertThat(executionEvents).has(conditions[i], atIndex(i));
		}
		softly.assertAll();
	}
