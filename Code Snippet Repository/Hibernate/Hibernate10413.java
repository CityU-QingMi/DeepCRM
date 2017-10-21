	@Test
	public void testCacheFindAfterEntitiesAtRevisionQuery() {
		IntTestEntity entFromQuery = (IntTestEntity) getAuditReader().createQuery()
				.forEntitiesAtRevision( IntTestEntity.class, 1 )
				.getSingleResult();

		IntTestEntity entFromFind = getAuditReader().find( IntTestEntity.class, id1, 1 );

		assert entFromFind == entFromQuery;
	}
