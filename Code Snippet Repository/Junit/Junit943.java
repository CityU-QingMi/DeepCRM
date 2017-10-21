	@Test
	void parseValidExcludedPackages() {
		// @formatter:off
		assertAll(
				() -> assertEquals(asList("org.junit.excluded"),
						parseArgLine("--exclude-package org.junit.excluded").getExcludedPackages()),
				() -> assertEquals(asList("org.junit.excluded"),
						parseArgLine("--exclude-package=org.junit.excluded").getExcludedPackages()),
				() -> assertEquals(asList("org.junit.excluded1", "org.junit.excluded2"),
						parseArgLine("--exclude-package org.junit.excluded1 --exclude-package org.junit.excluded2").getExcludedPackages())
		);
		// @formatter:on
	}
