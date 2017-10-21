	@Test
	@SuppressWarnings("")
	void assertThrowsWithExecutableThatThrowsSameExceptionTypeFromDifferentClassLoader() throws Exception {
		try (EnigmaClassLoader enigmaClassLoader = new EnigmaClassLoader()) {

			// Load expected exception type from different class loader
			Class<? extends Throwable> enigmaThrowableClass = (Class<? extends Throwable>) enigmaClassLoader.loadClass(
				EnigmaThrowable.class.getName());

			try {
				assertThrows(enigmaThrowableClass, () -> {
					throw new EnigmaThrowable();
				});
				expectAssertionFailedError();
			}
			catch (AssertionFailedError ex) {
				// Example Output:
				//
				// Unexpected exception type thrown ==>
				// expected: <org.junit.jupiter.api.EnigmaThrowable@5d3411d>
				// but was: <org.junit.jupiter.api.EnigmaThrowable@2471cca7>

				assertMessageStartsWith(ex, "Unexpected exception type thrown ==> ");
				// The presence of the "@" sign is sufficient to indicate that the hash was
				// generated to disambiguate between the two identical class names.
				assertMessageContains(ex, "expected: <org.junit.jupiter.api.EnigmaThrowable@");
				assertMessageContains(ex, "but was: <org.junit.jupiter.api.EnigmaThrowable@");
			}
		}
	}
