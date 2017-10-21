	@Test
	void parseValidUriSelectors() {
		// @formatter:off
		assertAll(
				() -> assertEquals(singletonList(new URI("file:///foo.txt")), parseArgLine("-u file:///foo.txt").getSelectedUris()),
				() -> assertEquals(singletonList(new URI("file:///foo.txt")), parseArgLine("--u file:///foo.txt").getSelectedUris()),
				() -> assertEquals(singletonList(new URI("file:///foo.txt")), parseArgLine("-select-uri file:///foo.txt").getSelectedUris()),
				() -> assertEquals(singletonList(new URI("file:///foo.txt")), parseArgLine("-select-uri=file:///foo.txt").getSelectedUris()),
				() -> assertEquals(singletonList(new URI("file:///foo.txt")), parseArgLine("--select-uri file:///foo.txt").getSelectedUris()),
				() -> assertEquals(singletonList(new URI("file:///foo.txt")), parseArgLine("--select-uri=file:///foo.txt").getSelectedUris()),
				() -> assertEquals(asList(new URI("file:///foo.txt"), new URI("http://localhost")), parseArgLine("-u file:///foo.txt -u http://localhost").getSelectedUris())
		);
		// @formatter:on
	}
