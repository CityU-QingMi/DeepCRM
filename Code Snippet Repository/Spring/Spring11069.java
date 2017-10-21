	@Test
	public void matching_LiteralPathElement() {
		checkMatches("foo", "foo");
		checkNoMatch("foo", "bar");
		checkNoMatch("foo", "/foo");
		checkNoMatch("/foo", "foo");
		checkMatches("/f", "/f");
		checkMatches("/foo", "/foo");
		checkNoMatch("/foo", "/food");
		checkNoMatch("/food", "/foo");
		checkMatches("/foo/", "/foo/");
		checkMatches("/foo/bar/woo", "/foo/bar/woo");
		checkMatches("foo/bar/woo", "foo/bar/woo");
	}
