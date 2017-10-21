	@Test
	void assertNotNullWithNullAndMessageSupplier() {
		try {
			assertNotNull(null, () -> "test");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "test");
			assertMessageEndsWith(ex, "expected: not <null>");
		}
	}
