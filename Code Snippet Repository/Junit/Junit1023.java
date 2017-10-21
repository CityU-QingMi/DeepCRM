	@Test
	void excludePackageWithMultiplePackages() {
		String excludedPackage1 = "java.lang";
		String excludedPackage2 = "java.util";
		PackageNameFilter filter = PackageNameFilter.excludePackageNames(excludedPackage1, excludedPackage2);

		assertThat(filter).hasToString(
			"Excludes package names that start with '" + excludedPackage1 + "' OR '" + excludedPackage2 + "'");

		assertTrue(filter.apply("java.lang.String").excluded());
		assertFalse(filter.toPredicate().test("java.lang.String"));
		assertThat(filter.apply("java.lang.String").getReason()).contains(
			"Package name [java.lang.String] matches excluded name: '" + excludedPackage1 + "'");

		assertTrue(filter.apply("java.util.Collection").excluded());
		assertFalse(filter.toPredicate().test("java.util.Collection"));
		assertThat(filter.apply("java.util.Collection").getReason()).contains(
			"Package name [java.util.Collection] matches excluded name: '" + excludedPackage2 + "'");

		assertTrue(filter.apply("java.util.function.Consumer").excluded());
		assertFalse(filter.toPredicate().test("java.util.function.Consumer"));
		assertThat(filter.apply("java.util.function.Consumer").getReason()).contains(
			"Package name [java.util.function.Consumer] matches excluded name: '" + excludedPackage2 + "'");

		assertTrue(filter.apply("java.time.Instant").included());
		assertTrue(filter.toPredicate().test("java.time.Instant"));
		assertThat(filter.apply("java.time.Instant").getReason()).contains(
			"Package name [java.time.Instant] does not match any excluded names: '" + excludedPackage1 + "' OR '"
					+ excludedPackage2 + "'");

		assertTrue(filter.apply("java.language.Test").included());
		assertTrue(filter.toPredicate().test("java.language.Test"));
		assertThat(filter.apply("java.language.Test").getReason()).contains(
			"Package name [java.language.Test] does not match any excluded names: '" + excludedPackage1 + "' OR '"
					+ excludedPackage2 + "'");
	}
