	@Test
	void parseValidFileSelectors() {
		// @formatter:off
		assertAll(
				() -> assertEquals(singletonList("foo.txt"), parseArgLine("-f foo.txt").getSelectedFiles()),
				() -> assertEquals(singletonList("foo.txt"), parseArgLine("--f foo.txt").getSelectedFiles()),
				() -> assertEquals(singletonList("foo.txt"), parseArgLine("-select-file foo.txt").getSelectedFiles()),
				() -> assertEquals(singletonList("foo.txt"), parseArgLine("-select-file=foo.txt").getSelectedFiles()),
				() -> assertEquals(singletonList("foo.txt"), parseArgLine("--select-file foo.txt").getSelectedFiles()),
				() -> assertEquals(singletonList("foo.txt"), parseArgLine("--select-file=foo.txt").getSelectedFiles()),
				() -> assertEquals(asList("foo.txt", "bar.csv"), parseArgLine("-f foo.txt -f bar.csv").getSelectedFiles())
		);
		// @formatter:on
	}
