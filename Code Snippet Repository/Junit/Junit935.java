	@Test
	void parseClasspathScanningEntries() {
		Path dir = Paths.get(".");
		// @formatter:off
		assertAll(
			() -> assertTrue(parseArgLine("--scan-class-path").isScanClasspath()),
			() -> assertEquals(emptyList(), parseArgLine("--scan-class-path").getSelectedClasspathEntries()),
			() -> assertTrue(parseArgLine("--scan-classpath").isScanClasspath()),
			() -> assertEquals(emptyList(), parseArgLine("--scan-classpath").getSelectedClasspathEntries()),
			() -> assertTrue(parseArgLine("--scan-class-path .").isScanClasspath()),
			() -> assertEquals(singletonList(dir), parseArgLine("--scan-class-path .").getSelectedClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("--scan-class-path=.").getSelectedClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("-scan-class-path .").getSelectedClasspathEntries()),
			() -> assertEquals(singletonList(dir), parseArgLine("-scan-class-path=.").getSelectedClasspathEntries()),
			() -> assertEquals(asList(dir, Paths.get("src/test/java")), parseArgLine("--scan-class-path . --scan-class-path src/test/java").getSelectedClasspathEntries()),
			() -> assertEquals(asList(dir, Paths.get("src/test/java")), parseArgLine("--scan-class-path ." + File.pathSeparator + "src/test/java").getSelectedClasspathEntries())
		);
		// @formatter:on
	}
