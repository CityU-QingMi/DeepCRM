	@Test
	public void patternPropertyGetLengthTests() {
		// Test all basic section types
		assertEquals(1, parse("{foo}").getNormalizedLength());
		assertEquals(3, parse("foo").getNormalizedLength());
		assertEquals(1, parse("{*foobar}").getNormalizedLength());
		assertEquals(1, parse("/{*foobar}").getNormalizedLength());
		assertEquals(1, parse("/**").getNormalizedLength());
		assertEquals(5, parse("{abc}asdf").getNormalizedLength());
		assertEquals(3, parse("{abc}_*").getNormalizedLength());
		assertEquals(3, parse("{abc}_{def}").getNormalizedLength());
		assertEquals(1, parse("/").getNormalizedLength());
		assertEquals(3, parse("a?b").getNormalizedLength());
		assertEquals(1, parse("*").getNormalizedLength());

		// Test on full templates
		assertEquals(8, parse("/foo/bar").getNormalizedLength());
		assertEquals(2, parse("/{foo}").getNormalizedLength());
		assertEquals(4, parse("/{foo}/{bar}").getNormalizedLength());
		assertEquals(16, parse("/{foo}/{bar}_{goo}_{wibble}/abc/bar").getNormalizedLength());
	}
