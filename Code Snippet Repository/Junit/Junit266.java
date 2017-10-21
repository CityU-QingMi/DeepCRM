	@Test
	void assertFalseWithBooleanSupplierTrueAndMessageSupplier() {
		try {
			assertFalse(() -> true, () -> "test");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "test");
		}
	}
