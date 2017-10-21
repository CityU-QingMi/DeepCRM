	@Test
	void assertTimeoutPreemptivelyWithMessageForSupplierThatCompletesAfterTheTimeout() {
		AssertionFailedError error = assertThrows(AssertionFailedError.class, () -> {
			assertTimeoutPreemptively(ofMillis(50), () -> {
				Thread.sleep(100);
				return "Tempus Fugit";
			}, "Tempus Fugit");
			fail("timeout exception should be thrown");
		});
		assertMessageEquals(error, "Tempus Fugit ==> execution timed out after 50 ms");
	}
