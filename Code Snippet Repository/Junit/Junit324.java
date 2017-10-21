	@Test
	void assertTimeoutWithMessageForSupplierThatCompletesAfterTheTimeout() {
		AssertionFailedError error = assertThrows(AssertionFailedError.class, () -> {
			assertTimeout(ofMillis(50), () -> {
				Thread.sleep(100);
				return "Tempus Fugit";
			}, "Tempus Fugit");
			fail("timeout assertion should be thrown");
		});
		assertMessageStartsWith(error, "Tempus Fugit ==> execution exceeded timeout of 50 ms by");
	}
