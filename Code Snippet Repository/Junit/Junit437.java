	@Test
	void getTestInstanceLifecyclePreconditions() {
		PreconditionViolationException exception = assertThrows(PreconditionViolationException.class,
			() -> getTestInstanceLifecycle(null, mock(ConfigurationParameters.class)));
		assertThat(exception).hasMessage("testClass must not be null");

		exception = assertThrows(PreconditionViolationException.class,
			() -> getTestInstanceLifecycle(getClass(), null));
		assertThat(exception).hasMessage("ConfigurationParameters must not be null");
	}
