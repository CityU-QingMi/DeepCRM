	@Test
	void assertThrowsWithExecutableThatDoesNotThrowAnExceptionWithMessageString() {
		try {
			assertThrows(IOException.class, nix, "Custom message");
			expectAssertionFailedError();
		}
		catch (AssertionError ex) {
			assertMessageEquals(ex,
				"Custom message ==> Expected java.io.IOException to be thrown, but nothing was thrown.");
		}
	}
