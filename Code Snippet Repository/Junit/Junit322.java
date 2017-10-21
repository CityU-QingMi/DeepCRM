	@Test
	void assertTimeoutForSupplierThatThrowsAnAssertionFailedError() {
		AssertionFailedError exception = assertThrows(AssertionFailedError.class, () -> {
			assertTimeout(ofMillis(500), () -> {
				fail("enigma");
				return "Tempus Fugit";
			});
			fail("assertion failed should be thrown");
		});
		assertMessageEquals(exception, "enigma");
	}
