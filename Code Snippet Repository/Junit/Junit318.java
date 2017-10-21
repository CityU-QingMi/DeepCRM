	@Test
	void assertTimeoutPreemptivelyForSupplierThatCompletesAfterTheTimeout() {
		AssertionFailedError error = assertThrows(AssertionFailedError.class, () -> {
			assertTimeoutPreemptively(ofMillis(50), () -> {
				Thread.sleep(100);
				return "Tempus Fugit";
			});
			fail("timeout exception should be thrown");
		});
		assertMessageEquals(error, "execution timed out after 50 ms");
	}
