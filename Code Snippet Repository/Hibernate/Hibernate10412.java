	@Test
	public void testCacheFindAfterRevisionsOfEntityQuery() {
		List entsFromQuery = getAuditReader().createQuery()
				.forRevisionsOfEntity( IntTestEntity.class, true, false )
				.getResultList();

		IntTestEntity entFromFindRev1 = getAuditReader().find( IntTestEntity.class, id1, 1 );
		IntTestEntity entFromFindRev2 = getAuditReader().find( IntTestEntity.class, id1, 2 );

		assert entFromFindRev1 == entsFromQuery.get( 0 );
		assert entFromFindRev2 == entsFromQuery.get( 1 );
	}
