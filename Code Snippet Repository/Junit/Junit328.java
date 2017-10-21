	@Test
	void assertTimeoutPreemptivelyForSupplierThatThrowsAnAssertionFailedError() {
		AssertionFailedError exception = assertThrows(AssertionFailedError.class, () -> {
			assertTimeoutPreemptively(ofMillis(500), () -> {
				fail("enigma");
				return "Tempus Fugit";
			});
			fail("assertion exception should be thrown");
		});
		assertMessageEquals(exception, "enigma");
	}
