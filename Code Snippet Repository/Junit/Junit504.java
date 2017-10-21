	@Test
	void beforeAllAndAfterAllCallbacks() {
		// @formatter:off
		assertBeforeAllAndAfterAllCallbacks(TopLevelTestCase.class,
			"fooBeforeAllCallback",
			"barBeforeAllCallback",
				"beforeAllMethod-1",
					"test-1",
				"afterAllMethod-1",
			"barAfterAllCallback",
			"fooAfterAllCallback"
		);
		// @formatter:on

		assertThat(actualExceptionInAfterAllCallback).isEmpty();
	}
