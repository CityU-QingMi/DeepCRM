	@Test
	void excludeTagsWithInvalidSyntax() {
		// @formatter:off
		assertAll(
			() -> assertSyntaxViolationForExcludes(null),
			() -> assertSyntaxViolationForExcludes(""),
			() -> assertSyntaxViolationForExcludes("   "),
			() -> assertSyntaxViolationForExcludes("foo bar")
		);
		// @formatter:on
	}
