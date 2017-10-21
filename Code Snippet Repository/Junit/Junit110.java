	@Test
	void assertAllWithExecutablesThatThrowAssertionErrors() {
		// @formatter:off
		MultipleFailuresError multipleFailuresError = assertThrows(MultipleFailuresError.class, () ->
			assertAll(
				() -> assertFalse(true),
				() -> assertFalse(true)
			)
		);
		// @formatter:on

		assertExpectedExceptionTypes(multipleFailuresError, AssertionFailedError.class, AssertionFailedError.class);
	}
