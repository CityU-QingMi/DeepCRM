	@After
	public final void afterTest() throws Exception {
		completeStrayTransaction();

		if ( isCleanupTestDataRequired() ) {
			cleanupTestData();
		}
		cleanupTest();

		cleanupSession();

		assertAllDataRemoved();
	}
