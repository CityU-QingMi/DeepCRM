	@Test
	void beforeAllAndAfterAllCallbacksInSubclass() {
		// @formatter:off
		assertBeforeAllAndAfterAllCallbacks(SecondLevelTestCase.class,
			"fooBeforeAllCallback",
			"barBeforeAllCallback",
				"bazBeforeAllCallback",
					"beforeAllMethod-1",
						"beforeAllMethod-2",
							"test-2",
						"afterAllMethod-2",
					"afterAllMethod-1",
				"bazAfterAllCallback",
			"barAfterAllCallback",
			"fooAfterAllCallback"
		);
		// @formatter:on

		assertThat(actualExceptionInAfterAllCallback).isEmpty();
	}
