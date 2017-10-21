	@Test
	void assertEqualsDoubleWithDeltaWithUnequalValuesAndMessage() {
		Executable assertion = () -> assertEquals(42.42d, 42.4d, 0.001d, "message");

		AssertionFailedError e = assertThrows(AssertionFailedError.class, assertion);

		assertMessageStartsWith(e, "message");
		assertMessageEndsWith(e, "expected: <42.42> but was: <42.4>");
		assertExpectedAndActualValues(e, 42.42d, 42.4d);
	}
