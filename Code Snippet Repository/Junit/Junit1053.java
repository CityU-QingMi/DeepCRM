	@Test
	void executeSafelyAborted() {
		TestAbortedException testAbortedException = new TestAbortedException("assumption violated");

		TestExecutionResult result = new SingleTestExecutor().executeSafely(() -> {
			throw testAbortedException;
		});

		assertEquals(ABORTED, result.getStatus());
		assertSame(testAbortedException, result.getThrowable().get());
	}
