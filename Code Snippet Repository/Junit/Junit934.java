	@Test
	void parseValidClasspathResourceSelectors() {
		// @formatter:off
		assertAll(
				() -> assertEquals(singletonList("/foo.csv"), parseArgLine("-r /foo.csv").getSelectedClasspathResources()),
				() -> assertEquals(singletonList("/foo.csv"), parseArgLine("--r /foo.csv").getSelectedClasspathResources()),
				() -> assertEquals(singletonList("/foo.csv"), parseArgLine("-select-resource /foo.csv").getSelectedClasspathResources()),
				() -> assertEquals(singletonList("/foo.csv"), parseArgLine("-select-resource=/foo.csv").getSelectedClasspathResources()),
				() -> assertEquals(singletonList("/foo.csv"), parseArgLine("--select-resource /foo.csv").getSelectedClasspathResources()),
				() -> assertEquals(singletonList("/foo.csv"), parseArgLine("--select-resource=/foo.csv").getSelectedClasspathResources()),
				() -> assertEquals(asList("/foo.csv", "bar.json"), parseArgLine("-r /foo.csv -r bar.json").getSelectedClasspathResources())
		);
		// @formatter:on
	}
