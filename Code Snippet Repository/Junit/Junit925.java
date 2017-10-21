	@Test
	void parseValidAdditionalClasspathEntries() {
		Path dir = Paths.get(".");
		// @formatter:off
		assertAll(
			() -> assertEquals(singletonList(dir), parseArgLine("-cp .").getAdditionalClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("--cp .").getAdditionalClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("-classpath .").getAdditionalClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("-classpath=.").getAdditionalClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("--classpath .").getAdditionalClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("--classpath=.").getAdditionalClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("--class-path .").getAdditionalClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("--class-path=.").getAdditionalClasspathEntries()),
			() -> assertEquals(asList(dir, Paths.get("src", "test", "java")), parseArgLine("-cp . -cp src/test/java").getAdditionalClasspathEntries()),
			() -> assertEquals(asList(dir, Paths.get("src", "test", "java")), parseArgLine("-cp ." + File.pathSeparator + "src/test/java").getAdditionalClasspathEntries())
		);
		// @formatter:on
	}
