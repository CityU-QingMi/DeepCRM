	@Test
	void reportTypeMismatchBetweenParameterAndResolvedParameter() {
		testMethodWithASingleStringParameter();
		thereIsAParameterResolverThatResolvesTheParameterTo(BigDecimal.ONE);

		ParameterResolutionException caught = assertThrows(ParameterResolutionException.class, this::invokeMethod);

		// @formatter:off
		assertThat(caught.getMessage())
				.contains("resolved a value of type [java.math.BigDecimal] for parameter [java.lang.String")
				.contains("but a value assignment compatible with [java.lang.String] is required.");
		// @formatter:on
	}
