	@TestFactory
	Stream<DynamicTest> dynamicTestsFromIntStream() {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		// Generates tests for the first 10 even integers.
		return IntStream.iterate(0, n -> n + 2).limit(10)
			.mapToObj(n -> dynamicTest("test" + n, () -> assertTrue(n % 2 == 0)));
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
