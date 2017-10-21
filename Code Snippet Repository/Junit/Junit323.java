	@Test
	void assertTimeoutForSupplierThatCompletesAfterTheTimeout() {
		AssertionFailedError error = assertThrows(AssertionFailedError.class, () -> {
			assertTimeout(ofMillis(50), () -> {
				Thread.sleep(100);
				return "Tempus Fugit";
			});
			fail("timeout assertion should be thrown");
		});
		assertMessageStartsWith(error, "execution exceeded timeout of 50 ms by");
	}
