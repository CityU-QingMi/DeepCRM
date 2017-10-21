	@Test
	void instancePerClassConfiguredViaConfigParam() {
		Class<?> testClass = AssumedInstancePerClassTestCase.class;

		// Should fail by default...
		performAssertions(testClass, 2, 1, 0);

		// Should pass with the config param
		performAssertions(testClass, singletonMap(KEY, PER_CLASS.name()), 2, 0, 1, "beforeAll", "test", "afterAll");
	}
