	public static <T> Stream<DynamicTest> stream(Iterator<T> inputGenerator,
			Function<? super T, String> displayNameGenerator, ThrowingConsumer<? super T> testExecutor) {

		Preconditions.notNull(inputGenerator, "inputGenerator must not be null");
		Preconditions.notNull(displayNameGenerator, "displayNameGenerator must not be null");
		Preconditions.notNull(testExecutor, "testExecutor must not be null");

		// @formatter:off
		return StreamSupport.stream(spliteratorUnknownSize(inputGenerator, ORDERED), false)
				.map(input -> dynamicTest(displayNameGenerator.apply(input), () -> testExecutor.accept(input)));
		// @formatter:on
	}
