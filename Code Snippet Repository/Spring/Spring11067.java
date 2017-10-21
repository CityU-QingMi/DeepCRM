	@Test
	public void patternPropertyGetWildcardCountTests() {
		// Test all basic section types
		assertEquals(computeScore(1, 0), parse("{foo}").getScore());
		assertEquals(computeScore(0, 0), parse("foo").getScore());
		assertEquals(computeScore(0, 0), parse("{*foobar}").getScore());
//		assertEquals(1,parse("/**").getScore());
		assertEquals(computeScore(1, 0), parse("{abc}asdf").getScore());
		assertEquals(computeScore(1, 1), parse("{abc}_*").getScore());
		assertEquals(computeScore(2, 0), parse("{abc}_{def}").getScore());
		assertEquals(computeScore(0, 0), parse("/").getScore());
		assertEquals(computeScore(0, 0), parse("a?b").getScore()); // currently deliberate
		assertEquals(computeScore(0, 1), parse("*").getScore());

		// Test on full templates
		assertEquals(computeScore(0, 0), parse("/foo/bar").getScore());
		assertEquals(computeScore(1, 0), parse("/{foo}").getScore());
		assertEquals(computeScore(2, 0), parse("/{foo}/{bar}").getScore());
		assertEquals(computeScore(4, 0), parse("/{foo}/{bar}_{goo}_{wibble}/abc/bar").getScore());
		assertEquals(computeScore(4, 3), parse("/{foo}/*/*_*/{bar}_{goo}_{wibble}/abc/bar").getScore());
	}
