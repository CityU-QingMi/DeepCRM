	@Test
	void instancePerClassConfiguredViaConfigParamThatOverridesSystemProperty() {
		Class<?> testClass = AssumedInstancePerClassTestCase.class;

		// Should fail with system property
		System.setProperty(KEY, PER_METHOD.name());
		performAssertions(testClass, 2, 1, 0);

		// Should pass with the config param
		performAssertions(testClass, singletonMap(KEY, PER_CLASS.name()), 2, 0, 1, "beforeAll", "test", "afterAll");
	}
