	@Test
	public void testGetAssociationWithEntityNameInNewSession() {
		//force a new session and AR
		forceNewSession();

		loadDataOnSessionAndAuditReader();

		checkEntities();

		checkEntityNames();

	}
