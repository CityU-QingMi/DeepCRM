	@Test
	void assertTimeoutForSupplierThatCompletesBeforeTheTimeout() {
		changed.get().set(false);
		String result = assertTimeout(ofMillis(500), () -> {
			changed.get().set(true);
			return "Tempus Fugit";
		});
		assertTrue(changed.get().get(), "should have executed in the same thread");
		assertEquals("Tempus Fugit", result);
	}
