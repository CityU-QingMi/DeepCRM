	@Test
	@SuppressWarnings("")
	void assertThrowsWithExecutableThatThrowsInstanceOfAnonymousInnerClassAsUnexpectedException() {
		try {
			assertThrows(IllegalStateException.class, () -> {
				throw new NumberFormatException() {
				};
			});
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "Unexpected exception type thrown ==> ");
			assertMessageContains(ex, "expected: <java.lang.IllegalStateException>");
			// As of the time of this writing, the class name of the above anonymous inner
			// class is org.junit.jupiter.api.AssertionsAssertThrowsTests$2; however, hard
			// coding "$2" is fragile. So we just check for the presence of the "$"
			// appended to this class's name.
			assertMessageContains(ex, "but was: <" + getClass().getName() + "$");
		}
	}
