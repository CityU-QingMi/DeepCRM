	@Test
	void assertEqualsDoubleWithDeltaWithUnequalValues() {
		AssertionFailedError e1 = assertThrows(AssertionFailedError.class, () -> assertEquals(9.9d, 9.7d, 0.1d));
		assertMessageEndsWith(e1, "expected: <9.9> but was: <9.7>");
		assertExpectedAndActualValues(e1, 9.9d, 9.7d);

		AssertionFailedError e2 = assertThrows(AssertionFailedError.class, () -> assertEquals(0.1d, 0.05d, 0.001d));
		assertMessageEndsWith(e2, "expected: <0.1> but was: <0.05>");
		assertExpectedAndActualValues(e2, 0.1d, 0.05d);

		AssertionFailedError e3 = assertThrows(AssertionFailedError.class, () -> assertEquals(17.11d, 15.11d, 1.1d));
		assertMessageEndsWith(e3, "expected: <17.11> but was: <15.11>");
		assertExpectedAndActualValues(e3, 17.11d, 15.11d);

		AssertionFailedError e4 = assertThrows(AssertionFailedError.class, () -> assertEquals(-7.2d, -5.9d, 1.1d));
		assertMessageEndsWith(e4, "expected: <-7.2> but was: <-5.9>");
		assertExpectedAndActualValues(e4, -7.2d, -5.9d);

		AssertionFailedError e5 = assertThrows(AssertionFailedError.class, () -> assertEquals(+0.0d, -0.001d, .00001d));
		assertMessageEndsWith(e5, "expected: <0.0> but was: <-0.001>");
		assertExpectedAndActualValues(e5, +0.0d, -0.001d);
	}
