	@Test
	void beforeAllCallbackThrowsAnException() {
		// @formatter:off
		assertBeforeAllAndAfterAllCallbacks(ExceptionInBeforeAllCallbackTestCase.class, 0, 0,
			"fooBeforeAllCallback",
			"exceptionThrowingBeforeAllCallback", // throws an exception.
				// beforeAllMethod should not get invoked.
					// test should not get invoked.
				// afterAllMethod should not get invoked.
			"fooAfterAllCallback"
		);
		// @formatter:on

		assertThat(actualExceptionInAfterAllCallback).containsInstanceOf(EnigmaException.class);
	}
