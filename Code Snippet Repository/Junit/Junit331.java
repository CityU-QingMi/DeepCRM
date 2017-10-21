	@Test
	void assertTrueWithBooleanSupplierFalseAndString() {
		try {
			assertTrue(() -> false, "test");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "test");
		}
	}
