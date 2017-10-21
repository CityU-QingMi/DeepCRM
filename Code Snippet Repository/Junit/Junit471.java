	@Test
	void onlyConsiderParameterResolversThatSupportAParticularParameter() {
		testMethodWithASingleStringParameter();
		thereIsAParameterResolverThatDoesNotSupportThisParameter();
		thereIsAParameterResolverThatResolvesTheParameterTo("something");

		invokeMethod();

		verify(instance).singleStringParameter("something");
	}
