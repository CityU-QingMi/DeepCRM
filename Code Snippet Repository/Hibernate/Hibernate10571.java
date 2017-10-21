	@Test
	public void testObtainEntityNameAssociationWithEntityNameAndNotAuditedModeInNewSession() {
		//force a new session and AR
		forceNewSession();

		loadDataOnSessionAndAuditReader();

		checkEntities();

		checkEntityNames();

	}
