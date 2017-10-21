	@Test
	void wrapAllExceptionsThrownDuringParameterResolutionIntoAParameterResolutionException() {
		anyTestMethodWithAtLeastOneParameter();
		IllegalArgumentException cause = anyExceptionButParameterResolutionException();
		throwDuringParameterResolution(cause);

		ParameterResolutionException caught = assertThrows(ParameterResolutionException.class, this::invokeMethod);

		assertSame(cause, caught.getCause(), () -> "cause should be present");
		assertThat(caught.getMessage()).startsWith("Failed to resolve parameter [java.lang.String");
	}
