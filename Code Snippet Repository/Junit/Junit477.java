	@Test
	void doNotWrapThrownExceptionIfItIsAlreadyAParameterResolutionException() {
		anyTestMethodWithAtLeastOneParameter();
		ParameterResolutionException cause = new ParameterResolutionException("custom message");
		throwDuringParameterResolution(cause);

		ParameterResolutionException caught = assertThrows(ParameterResolutionException.class, this::invokeMethod);

		assertSame(cause, caught);
	}
