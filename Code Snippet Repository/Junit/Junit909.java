	@Test
	void controlCharacters() {
		// @formatter:off
		assertAll("ISO Control Characters",
			() -> shouldContainIsoControlCharacter("\u005Ct"), // horizontal tab
			() -> shouldContainIsoControlCharacter("\t"),
			() -> shouldContainIsoControlCharacter("\u005Cn"), // line feed
			() -> shouldContainIsoControlCharacter("\n"),
			() -> shouldContainIsoControlCharacter("\u005Cf"), // form feed
			() -> shouldContainIsoControlCharacter("\f"),
			() -> shouldContainIsoControlCharacter("\u005Cr"), // carriage return
			() -> shouldContainIsoControlCharacter("\r"),
			() -> shouldNotContainIsoControlCharacter(null),
			() -> shouldNotContainIsoControlCharacter(""),
			() -> shouldNotContainIsoControlCharacter("hello-world"),
			() -> shouldNotContainIsoControlCharacter("0123456789"),
			() -> shouldNotContainIsoControlCharacter("$-_=+!@.,"),
			() -> shouldNotContainIsoControlCharacter("   "),
			() -> shouldNotContainIsoControlCharacter("hello world")
		);
		// @formatter:on
	}
