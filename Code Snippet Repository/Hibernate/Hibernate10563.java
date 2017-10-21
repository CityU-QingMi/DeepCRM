	@Test
	public void testObtainEntityNameCollectionWithEntityNameAndNotAuditedModeInNewSession() {
		// force new session and AR
		forceNewSession();

		loadDataOnSessionAndAuditReader();

		checkEntityNames();


	}
