	@AfterEach
	default void afterEachTest(TestInfo testInfo) {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		LOG.info(() -> String.format("Finished executing [%s]",
			testInfo.getDisplayName()));
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
