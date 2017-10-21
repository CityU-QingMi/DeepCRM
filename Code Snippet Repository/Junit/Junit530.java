	@Test
	void twoTestClassesCanShareStateViaEngineExtensionContext() {
		Parent.counter.set(0);

		ExecutionEventRecorder eventRecorder = executeTests(
			request().selectors(selectClass(A.class), selectClass(B.class)).build());

		assertThat(eventRecorder.getTestFinishedCount()).isEqualTo(2);
		assertThat(Parent.counter).hasValue(1);
	}
