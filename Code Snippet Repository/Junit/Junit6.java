	@TestFactory
	Stream<DynamicTest> dynamicTestsFromStream() {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		return Stream.of("A", "B", "C")
			.map(str -> dynamicTest("test" + str, () -> { /* ... */ }));
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
