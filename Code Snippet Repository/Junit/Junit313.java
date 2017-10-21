	@Test
	void assertThrowsWithExecutableThatThrowsAnUnexpectedExceptionWithMessageSupplier() {
		try {
			assertThrows(IllegalStateException.class, () -> {
				throw new NumberFormatException();
			}, () -> "Custom message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			// Should look something like this:
			// Custom message ==> Unexpected exception type thrown ==> expected: <java.lang.IllegalStateException> but was: <java.lang.NumberFormatException>
			assertMessageStartsWith(ex, "Custom message ==> ");
			assertMessageContains(ex, "Unexpected exception type thrown ==> ");
			assertMessageContains(ex, "expected: <java.lang.IllegalStateException>");
			assertMessageContains(ex, "but was: <java.lang.NumberFormatException>");
		}
	}
