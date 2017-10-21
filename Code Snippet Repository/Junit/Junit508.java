	@Test
	void beforeAllMethodThrowsAnException() {
		// @formatter:off
		assertBeforeAllAndAfterAllCallbacks(ExceptionInBeforeAllMethodTestCase.class, 0, 0,
			"fooBeforeAllCallback",
				"beforeAllMethod", // throws an exception.
					// test should not get invoked.
				"afterAllMethod",
			"fooAfterAllCallback"
		);
		// @formatter:on

		assertThat(actualExceptionInAfterAllCallback).containsInstanceOf(EnigmaException.class);
	}
