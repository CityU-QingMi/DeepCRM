	@Test
	public void testRevisionPropertyRestriction() {
		List<Object[]> result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.add( AuditEntity.id().eq( id1 ) )
				.add( AuditEntity.revisionProperty( "customTimestamp" ).ge( timestamp ) )
				.getResultList();

		assert result.get( 0 )[0].equals( new StrIntTestEntity( "c", 10, id1 ) );
		assert result.get( 0 )[1] instanceof CustomRevEntity;
		assert ((CustomRevEntity) result.get( 0 )[1]).getCustomId() == 2;
		assert ((CustomRevEntity) result.get( 0 )[1]).getCustomTimestamp() >= timestamp;
	}
