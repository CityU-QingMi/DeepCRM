	@Test
	void includePackageWithMultiplePackages() {
		String includedPackage1 = "java.lang";
		String includedPackage2 = "java.util";
		PackageNameFilter filter = PackageNameFilter.includePackageNames(includedPackage1, includedPackage2);

		assertThat(filter).hasToString("Includes package names that matches all packages that start with '"
				+ includedPackage1 + "' OR '" + includedPackage2 + "'");

		assertTrue(filter.apply("java.lang.String").included());
		assertTrue(filter.toPredicate().test("java.lang.String"));
		assertThat(filter.apply("java.lang.String").getReason()).contains(
			"Package name [java.lang.String] matches included name: '" + includedPackage1 + "'");

		assertTrue(filter.apply("java.util.Collection").included());
		assertTrue(filter.toPredicate().test("java.util.Collection"));
		assertThat(filter.apply("java.util.Collection").getReason()).contains(
			"Package name [java.util.Collection] matches included name: '" + includedPackage2 + "'");

		assertTrue(filter.apply("java.util.function.Consumer").included());
		assertTrue(filter.toPredicate().test("java.util.function.Consumer"));
		assertThat(filter.apply("java.util.function.Consumer").getReason()).contains(
			"Package name [java.util.function.Consumer] matches included name: '" + includedPackage2 + "'");

		assertFalse(filter.apply("java.time.Instant").included());
		assertFalse(filter.toPredicate().test("java.time.Instant"));
		assertThat(filter.apply("java.time.Instant").getReason()).contains(
			"Package name [java.time.Instant] does not match any included names: '" + includedPackage1 + "' OR '"
					+ includedPackage2 + "'");

		assertFalse(filter.apply("java.language.Test").included());
		assertFalse(filter.toPredicate().test("java.language.Test"));
		assertThat(filter.apply("java.language.Test").getReason()).contains(
			"Package name [java.language.Test] does not match any included names: '" + includedPackage1 + "' OR '"
					+ includedPackage2 + "'");
	}
