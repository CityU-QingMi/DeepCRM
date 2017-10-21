	@Test
	void executeSafelyFailed() {
		AssertionError assertionError = new AssertionError("assumption violated");

		TestExecutionResult result = new SingleTestExecutor().executeSafely(() -> {
			throw assertionError;
		});

		assertEquals(FAILED, result.getStatus());
		assertSame(assertionError, result.getThrowable().get());
	}
