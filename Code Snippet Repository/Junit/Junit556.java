	@Test
	void exceptionsDuringMethodLookupAreWrappedAndThrown() {
		AbstractTestRuleAdapter adapter = new AbstractTestRuleAdapter(
			new SimpleRuleAnnotatedMember(new ErrorCollector()), Verifier.class) {

			@Override
			public void before() {
				super.executeMethod("foo");
			}
		};

		JUnitException exception = assertThrows(JUnitException.class, adapter::before);

		assertEquals(exception.getMessage(), "Failed to find method foo() in class org.junit.rules.ErrorCollector");
	}
