	@Test
	public void testSelectEntitiesQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, true, false )
				.add( AuditEntity.id().eq( id1 ) )
				.getResultList();

		assert result.size() == 2;

		assert result.get( 0 ).equals( new StrIntTestEntity( "a", 10, id1 ) );
		assert result.get( 1 ).equals( new StrIntTestEntity( "aBc", 10, id1 ) );
	}
