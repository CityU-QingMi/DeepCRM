	@Test
	void parseValidDetailsTheme() {
		// @formatter:off
		assertAll(
			() -> assertEquals(Theme.ASCII, parseArgLine("--details-theme ascii").getTheme()),
			() -> assertEquals(Theme.ASCII, parseArgLine("--details-theme ASCII").getTheme()),
			() -> assertEquals(Theme.UNICODE, parseArgLine("--details-theme unicode").getTheme()),
			() -> assertEquals(Theme.UNICODE, parseArgLine("--details-theme UNICODE").getTheme()),
			() -> assertEquals(Theme.UNICODE, parseArgLine("--details-theme uniCode").getTheme())
		);
		// @formatter:on
	}
