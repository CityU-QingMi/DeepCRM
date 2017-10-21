	@Test
	public void basicMatching() {
		checkMatches("", "");
		checkMatches("", "/");
		checkMatches("", null);
		checkNoMatch("/abc", "/");
		checkMatches("/", "/");
		checkNoMatch("/", "/a");
		checkMatches("foo/bar/", "foo/bar/");
		checkNoMatch("foo", "foobar");
		checkMatches("/foo/bar", "/foo/bar");
		checkNoMatch("/foo/bar", "/foo/baz");
	}
