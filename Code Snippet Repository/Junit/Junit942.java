	@Test
	void parseValidIncludedPackages() {
		// @formatter:off
		assertAll(
				() -> assertEquals(asList("org.junit.included"),
						parseArgLine("--include-package org.junit.included").getIncludedPackages()),
				() -> assertEquals(asList("org.junit.included"),
						parseArgLine("--include-package=org.junit.included").getIncludedPackages()),
				() -> assertEquals(asList("org.junit.included1", "org.junit.included2"),
						parseArgLine("--include-package org.junit.included1 --include-package org.junit.included2").getIncludedPackages())
		);
		// @formatter:on
	}
