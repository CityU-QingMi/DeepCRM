	@Test
	void assertEqualsFloatWithDeltaWithUnequalValuesAndMessage() {
		Executable assertion = () -> assertEquals(0.5f, 0.45f, 0.03f, "message");

		AssertionFailedError e = assertThrows(AssertionFailedError.class, assertion);

		assertMessageStartsWith(e, "message");
		assertMessageEndsWith(e, "expected: <0.5> but was: <0.45>");
		assertExpectedAndActualValues(e, 0.5f, 0.45f);
	}
