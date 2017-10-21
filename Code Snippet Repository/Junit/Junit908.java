	@Test
	void whitespace() {
		// @formatter:off
		assertAll("Whitespace",
			() -> shouldContainWhitespace("   "),
			() -> shouldContainWhitespace("\u005Ct"), // horizontal tab
			() -> shouldContainWhitespace("\t"),
			() -> shouldContainWhitespace("\u005Cn"), // line feed
			() -> shouldContainWhitespace("\n"),
			() -> shouldContainWhitespace("\u005Cf"), // form feed
			() -> shouldContainWhitespace("\f"),
			() -> shouldContainWhitespace("\u005Cr"), // carriage return
			() -> shouldContainWhitespace("\r"),
			() -> shouldContainWhitespace("hello world"),
			() -> shouldNotContainWhitespace(null),
			() -> shouldNotContainWhitespace(""),
			() -> shouldNotContainWhitespace("hello-world"),
			() -> shouldNotContainWhitespace("0123456789"),
			() -> shouldNotContainWhitespace("$-_=+!@.,")
		);
		// @formatter:on
	}
