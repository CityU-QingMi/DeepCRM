	@Test
	public void questionMarks() {
		checkNoMatch("a", "ab");
		checkMatches("/f?o/bar", "/foo/bar");
		checkNoMatch("/foo/b2r", "/foo/bar");
		checkNoMatch("?", "te");
		checkMatches("?", "a");
		checkMatches("???", "abc");
		checkNoMatch("tes?", "te");
		checkNoMatch("tes?", "tes");
		checkNoMatch("tes?", "testt");
		checkNoMatch("tes?", "tsst");
		checkMatches(".?.a", ".a.a");
		checkNoMatch(".?.a", ".aba");
		checkMatches("/f?o/bar","/f%20o/bar");
	}
