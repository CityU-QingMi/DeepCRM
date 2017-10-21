	@Test
	public void testSelectRevisionTypeQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.addProjection( AuditEntity.revisionType() )
				.add( AuditEntity.id().eq( id1 ) )
				.addOrder( AuditEntity.revisionNumber().asc() )
				.getResultList();

		assert result.size() == 3;

		assert result.get( 0 ).equals( RevisionType.ADD );
		assert result.get( 1 ).equals( RevisionType.MOD );
		assert result.get( 2 ).equals( RevisionType.DEL );
	}
