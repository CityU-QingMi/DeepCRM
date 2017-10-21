	@Test
	void executeWithExcludeClassnameOptionExcludesClasses() {
		String[] args = { "-e", "junit-jupiter", "-p", "org.junit.platform.console.subpackage", "--exclude-classname",
				"^org\\.junit\\.platform\\.console\\.subpackage\\..*" };
		ConsoleLauncherWrapperResult result = new ConsoleLauncherWrapper().execute(args);
		assertAll("all subpackage test classes are excluded by the class name filter", //
			() -> assertArrayEquals(args, result.args), //
			() -> assertEquals(StandardCharsets.UTF_8, result.charset), //
			() -> assertEquals(0, result.code), //
			() -> assertEquals(0, result.getTestsFoundCount()), //
			() -> assertTrue(isBlank(result.err)) //
		);
	}
