	@Test
	public void wildcards() {
		checkMatches("/*/bar", "/foo/bar");
		checkNoMatch("/*/bar", "/foo/baz");
		checkNoMatch("/*/bar", "//bar");
		checkMatches("/f*/bar", "/foo/bar");
		checkMatches("/*/bar", "/foo/bar");
		checkMatches("a/*","a/");
		checkMatches("/*","/");
		checkMatches("/*/bar", "/foo/bar");
		checkNoMatch("/*/bar", "/foo/baz");
		checkMatches("/f*/bar", "/foo/bar");
		checkMatches("/*/bar", "/foo/bar");
		checkMatches("/a*b*c*d/bar", "/abcd/bar");
		checkMatches("*a*", "testa");
		checkMatches("a/*", "a/");
		checkNoMatch("a/*", "a//"); // no data for *
		checkMatches("a/*", "a/a/"); // trailing slash, so is allowed
		PathPatternParser ppp = new PathPatternParser();
		ppp.setMatchOptionalTrailingSeparator(false);
		assertFalse(ppp.parse("a/*").matches(toPathContainer("a//")));
		checkMatches("a/*", "a/a");
		checkMatches("a/*", "a/a/"); // trailing slash is optional
		checkMatches("/resource/**", "/resource");
		checkNoMatch("/resource/**", "/resourceX");
		checkNoMatch("/resource/**", "/resourceX/foobar");
		checkMatches("/resource/**", "/resource/foobar");
	}
