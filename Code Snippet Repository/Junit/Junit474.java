	@Test
	void reportIfThereAreMultipleParameterResolversThatSupportTheParameter() {
		testMethodWithASingleStringParameter();
		thereIsAParameterResolverThatResolvesTheParameterTo("one");
		thereIsAParameterResolverThatResolvesTheParameterTo("two");

		ParameterResolutionException caught = assertThrows(ParameterResolutionException.class, this::invokeMethod);

		// @formatter:off
		assertThat(caught.getMessage())
				.contains("parameter [java.lang.String")
				.contains(ConfigurableParameterResolver.class.getName() + ", " + ConfigurableParameterResolver.class.getName());
		// @formatter:on
	}
