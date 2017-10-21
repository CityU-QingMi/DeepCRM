	@Test
	void includeTagsWithInvalidSyntax() {
		// @formatter:off
		assertAll(
			() -> assertSyntaxViolationForIncludes(null),
			() -> assertSyntaxViolationForIncludes(""),
			() -> assertSyntaxViolationForIncludes("   "),
			() -> assertSyntaxViolationForIncludes("foo bar")
		);
		// @formatter:on
	}
