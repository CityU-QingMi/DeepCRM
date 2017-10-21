	@Test
	void parseValidDirectorySelectors() {
		// @formatter:off
		assertAll(
				() -> assertEquals(singletonList("foo/bar"), parseArgLine("-d foo/bar").getSelectedDirectories()),
				() -> assertEquals(singletonList("foo/bar"), parseArgLine("--d foo/bar").getSelectedDirectories()),
				() -> assertEquals(singletonList("foo/bar"), parseArgLine("-select-directory foo/bar").getSelectedDirectories()),
				() -> assertEquals(singletonList("foo/bar"), parseArgLine("-select-directory=foo/bar").getSelectedDirectories()),
				() -> assertEquals(singletonList("foo/bar"), parseArgLine("--select-directory foo/bar").getSelectedDirectories()),
				() -> assertEquals(singletonList("foo/bar"), parseArgLine("--select-directory=foo/bar").getSelectedDirectories()),
				() -> assertEquals(asList("foo/bar", "bar/qux"), parseArgLine("-d foo/bar -d bar/qux").getSelectedDirectories())
		);
		// @formatter:on
	}
