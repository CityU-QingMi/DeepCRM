	@Test
	public void testObtainEntityNameAuditedCollectionWithEntityNameInNewSession() {
		// force a new session and AR
		forceNewSession();

		loadDataOnSessionAndAuditReader();

		checkEntityNames();

	}
