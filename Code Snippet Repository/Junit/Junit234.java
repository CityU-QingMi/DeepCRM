	@Test
	void assertEqualsCharWithUnequalValuesAndMessageSupplier() {
		try {
			assertEquals('a', 'b', () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected: <a> but was: <b>");
			assertExpectedAndActualValues(ex, 'a', 'b');
		}
	}
