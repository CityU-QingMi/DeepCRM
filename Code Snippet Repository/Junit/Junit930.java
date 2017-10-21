	@Test
	void parseValidPackageSelectors() {
		// @formatter:off
		assertAll(
				() -> assertEquals(singletonList("com.acme.foo"), parseArgLine("-p com.acme.foo").getSelectedPackages()),
				() -> assertEquals(singletonList("com.acme.foo"), parseArgLine("--p com.acme.foo").getSelectedPackages()),
				() -> assertEquals(singletonList("com.acme.foo"), parseArgLine("-select-package com.acme.foo").getSelectedPackages()),
				() -> assertEquals(singletonList("com.acme.foo"), parseArgLine("-select-package=com.acme.foo").getSelectedPackages()),
				() -> assertEquals(singletonList("com.acme.foo"), parseArgLine("--select-package com.acme.foo").getSelectedPackages()),
				() -> assertEquals(singletonList("com.acme.foo"), parseArgLine("--select-package=com.acme.foo").getSelectedPackages()),
				() -> assertEquals(asList("com.acme.foo", "com.example.bar"), parseArgLine("-p com.acme.foo -p com.example.bar").getSelectedPackages())
		);
		// @formatter:on
	}
