	@Test
	public void multipleSelectorsInPath() {
		checkNoMatch("/abc", "////abc");
		checkNoMatch("/", "//");
		checkNoMatch("/abc/def/ghi", "/abc//def///ghi");
		checkNoMatch("/abc", "////abc");
		checkMatches("////abc", "////abc");
		checkNoMatch("/", "//");
		checkNoMatch("/abc//def", "/abc/def");
		checkNoMatch("/abc//def///ghi", "/abc/def/ghi");
		checkMatches("/abc//def///ghi", "/abc//def///ghi");
	}
