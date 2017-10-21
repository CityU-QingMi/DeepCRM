	@Test
	void instancePerClassConfiguredViaSystemProperty() {
		Class<?> testClass = AssumedInstancePerClassTestCase.class;

		// Should fail by default...
		performAssertions(testClass, 2, 1, 0);

		// Should pass with the system property set
		System.setProperty(KEY, PER_CLASS.name());
		performAssertions(testClass, 2, 0, 1, "beforeAll", "test", "afterAll");
	}
