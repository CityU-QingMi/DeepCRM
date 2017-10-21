	@Test
	void validSyntax() {
		// @formatter:off
		assertAll("Valid Tag Syntax",
			() -> yep("fast"),
			() -> yep("super_fast"),
			() -> yep("unit-test"),
			() -> yep("integration.test"),
			() -> yep("org.example.CustomTagClass"),
			() -> yep("  surrounded-by-whitespace\t\n"),
			() -> nope(null),
			() -> nope(""),
			() -> nope("     "),
			() -> nope("\t"),
			() -> nope("\f"),
			() -> nope("\r"),
			() -> nope("\n"),
			() -> nope("custom tag"), // internal space
			() -> nope(","),          // comma
			() -> nope("("),          // opening parenthesis
			() -> nope(")"),          // closing parenthesis
			() -> nope("&"),          // boolean AND
			() -> nope("|"),          // boolean OR
			() -> nope("!")           // boolean NOT
		);
		// @formatter:on
	}
