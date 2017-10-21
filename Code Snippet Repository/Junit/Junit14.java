	@BeforeEach
	default void beforeEachTest(TestInfo testInfo) {
		// end::user_guide[]
		// @formatter:off
		// tag::user_guide[]
		LOG.info(() -> String.format("About to execute [%s]",
			testInfo.getDisplayName()));
		// end::user_guide[]
		// @formatter:on
		// tag::user_guide[]
	}
