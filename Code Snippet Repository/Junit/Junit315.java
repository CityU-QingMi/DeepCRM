	@Test
	void assertThrowsWithExecutableThatThrowsInstanceOfStaticNestedClassAsUnexpectedException() {
		try {
			assertThrows(IllegalStateException.class, () -> {
				throw new LocalException();
			});
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "Unexpected exception type thrown ==> ");
			assertMessageContains(ex, "expected: <java.lang.IllegalStateException>");
			// The following verifies that the canonical name is used (i.e., "." instead of "$").
			assertMessageContains(ex, "but was: <" + LocalException.class.getName().replace("$", ".") + ">");
		}
	}
