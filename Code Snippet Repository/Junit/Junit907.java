	@Test
	void blankness() {
		// @formatter:off
		assertAll("Blankness",
			() -> assertTrue(isBlank(null)),
			() -> assertTrue(isBlank("")),
			() -> assertTrue(isBlank(" \t\n\r")),
			() -> assertTrue(isNotBlank("."))
		);
		// @formatter:on
	}
