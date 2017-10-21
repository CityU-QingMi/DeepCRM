	@Test
	void assertEqualsFloatWithDeltaWithUnequalValues() {
		AssertionFailedError e1 = assertThrows(AssertionFailedError.class, () -> assertEquals(0.5f, 0.2f, 0.2f));
		assertMessageEndsWith(e1, "expected: <0.5> but was: <0.2>");

		AssertionFailedError e2 = assertThrows(AssertionFailedError.class, () -> assertEquals(0.1f, 0.2f, 0.000001f));
		assertMessageEndsWith(e2, "expected: <0.1> but was: <0.2>");

		AssertionFailedError e3 = assertThrows(AssertionFailedError.class, () -> assertEquals(100.0f, 50.0f, 10.0f));
		assertMessageEndsWith(e3, "expected: <100.0> but was: <50.0>");

		AssertionFailedError e4 = assertThrows(AssertionFailedError.class, () -> assertEquals(-3.5f, -3.3f, 0.01f));
		assertMessageEndsWith(e4, "expected: <-3.5> but was: <-3.3>");

		AssertionFailedError e5 = assertThrows(AssertionFailedError.class, () -> assertEquals(+0.0f, -0.001f, .00001f));
		assertMessageEndsWith(e5, "expected: <0.0> but was: <-0.001>");
	}
