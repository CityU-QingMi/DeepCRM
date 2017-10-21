	@Test
	void assertAllWithNullInExecutableArray() {
		try {
			// @formatter:off
			assertAll(
				() -> {},
				(Executable) null
			);
			// @formatter:on
		}
		catch (PreconditionViolationException ex) {
			assertMessageEquals(ex, "individual executables must not be null");
		}
	}
