	@Test
	void beforeAllAndAfterAllCallbacksInSubSubclass() {
		// @formatter:off
		assertBeforeAllAndAfterAllCallbacks(ThirdLevelTestCase.class,
			"fooBeforeAllCallback",
			"barBeforeAllCallback",
				"bazBeforeAllCallback",
					"quuxBeforeAllCallback",
						"beforeAllMethod-1",
							"beforeAllMethod-2",
								"beforeAllMethod-3",
									"test-3",
								"afterAllMethod-3",
							"afterAllMethod-2",
						"afterAllMethod-1",
					"quuxAfterAllCallback",
				"bazAfterAllCallback",
			"barAfterAllCallback",
			"fooAfterAllCallback"
		);
		// @formatter:on

		assertThat(actualExceptionInAfterAllCallback).isEmpty();
	}
