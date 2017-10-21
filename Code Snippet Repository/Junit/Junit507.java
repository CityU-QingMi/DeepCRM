	@Test
	void beforeAllAndAfterAllCallbacksInSubSubclassWithStaticMethodHiding() {
		// @formatter:off
		assertBeforeAllAndAfterAllCallbacks(ThirdLevelStaticHidingTestCase.class,
			"fooBeforeAllCallback",
			"barBeforeAllCallback",
				"bazBeforeAllCallback",
					"quuxBeforeAllCallback",
						"beforeAllMethod-1-hidden",
						"beforeAllMethod-2-hidden",
						"beforeAllMethod-3",
							"test-3",
						// The @AfterAll methods are executed as 1/2/3 due to
						// the "stable" method sort order on the Platform.
						"afterAllMethod-1-hidden",
						"afterAllMethod-2-hidden",
						"afterAllMethod-3",
					"quuxAfterAllCallback",
				"bazAfterAllCallback",
			"barAfterAllCallback",
			"fooAfterAllCallback"
		);
		// @formatter:on

		assertThat(actualExceptionInAfterAllCallback).isEmpty();
	}
