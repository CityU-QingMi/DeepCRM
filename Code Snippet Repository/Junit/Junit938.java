	@Test
	void parseValidDetails() {
		// @formatter:off
		assertAll(
			() -> assertEquals(Details.VERBOSE, parseArgLine("--details verbose").getDetails()),
			() -> assertEquals(Details.TREE, parseArgLine("--details tree").getDetails()),
			() -> assertEquals(Details.FLAT, parseArgLine("--details flat").getDetails()),
			() -> assertEquals(Details.NONE, parseArgLine("--details NONE").getDetails()),
			() -> assertEquals(Details.NONE, parseArgLine("--details none").getDetails()),
			() -> assertEquals(Details.NONE, parseArgLine("--details None").getDetails())
		);
		// @formatter:on
	}
