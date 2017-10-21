	@Test
	void assertEqualsDoubleWithDeltaWithUnequalValuesAndMessageSupplier() {
		Executable assertion = () -> assertEquals(0.9d, 10.12d, 5.001d, () -> "message");

		AssertionFailedError e = assertThrows(AssertionFailedError.class, assertion);

		assertMessageStartsWith(e, "message");
		assertMessageEndsWith(e, "expected: <0.9> but was: <10.12>");
		assertExpectedAndActualValues(e, 0.9d, 10.12d);
	}
