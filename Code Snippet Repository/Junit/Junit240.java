	@Test
	void assertEqualsFloatWithIllegalDelta() {
		AssertionFailedError e1 = assertThrows(AssertionFailedError.class, () -> assertEquals(0.1f, 0.2f, -0.9f));
		assertMessageEndsWith(e1, "positive delta expected but was: <-0.9>");

		AssertionFailedError e2 = assertThrows(AssertionFailedError.class, () -> assertEquals(.0f, .0f, -10.5f));
		assertMessageEndsWith(e2, "positive delta expected but was: <-10.5>");

		AssertionFailedError e3 = assertThrows(AssertionFailedError.class, () -> assertEquals(4.5f, 4.6f, Float.NaN));
		assertMessageEndsWith(e3, "positive delta expected but was: <NaN>");
	}
