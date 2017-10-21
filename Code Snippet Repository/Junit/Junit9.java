	@TestFactory
	Stream<DynamicNode> dynamicTestsWithContainers() {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		return Stream.of("A", "B", "C")
			.map(input -> dynamicContainer("Container " + input, Stream.of(
				dynamicTest("not null", () -> assertNotNull(input)),
				dynamicContainer("properties", Stream.of(
					dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
					dynamicTest("not empty", () -> assertFalse(input.isEmpty()))
				))
			)));
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
