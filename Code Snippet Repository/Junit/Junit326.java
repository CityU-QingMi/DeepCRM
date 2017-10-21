	@Test
	void assertTimeoutPreemptivelyForSupplierThatCompletesBeforeTheTimeout() {
		changed.get().set(false);
		String result = assertTimeoutPreemptively(ofMillis(500), () -> {
			changed.get().set(true);
			return "Tempus Fugit";
		});
		assertFalse(changed.get().get(), "should have executed in a different thread");
		assertEquals("Tempus Fugit", result);
	}
