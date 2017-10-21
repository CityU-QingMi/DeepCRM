	@Test
	void assertEqualsDoubleWithIllegalDelta() {
		AssertionFailedError e1 = assertThrows(AssertionFailedError.class, () -> assertEquals(1.1d, 1.11d, -0.5d));
		assertMessageEndsWith(e1, "positive delta expected but was: <-0.5>");

		AssertionFailedError e2 = assertThrows(AssertionFailedError.class, () -> assertEquals(.55d, .56d, -10.5d));
		assertMessageEndsWith(e2, "positive delta expected but was: <-10.5>");

		AssertionFailedError e3 = assertThrows(AssertionFailedError.class, () -> assertEquals(1.1d, 1.1d, Double.NaN));
		assertMessageEndsWith(e3, "positive delta expected but was: <NaN>");
	}
