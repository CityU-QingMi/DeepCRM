	@Test
	void reportThatNullIsNotAViableArgumentIfAPrimitiveTypeIsExpected() {
		testMethodWithASinglePrimitiveIntParameter();
		thereIsAParameterResolverThatResolvesTheParameterTo(null);

		ParameterResolutionException caught = assertThrows(ParameterResolutionException.class, this::invokeMethod);

		// @formatter:off
		assertThat(caught.getMessage())
				.contains("resolved a null value for parameter [int")
				.contains("but a primitive of type [int] is required.");
		// @formatter:on
	}
