	@Test
	public void testRevisionsOfId1Query() {
		List<Object[]> result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.add( AuditEntity.id().eq( id1 ) )
				.getResultList();

		assert result.get( 0 )[0].equals( new StrIntTestEntity( "a", 10, id1 ) );
		assert result.get( 0 )[1] instanceof CustomRevEntity;
		assert ((CustomRevEntity) result.get( 0 )[1]).getCustomId() == 1;

		assert result.get( 1 )[0].equals( new StrIntTestEntity( "c", 10, id1 ) );
		assert result.get( 1 )[1] instanceof CustomRevEntity;
		assert ((CustomRevEntity) result.get( 1 )[1]).getCustomId() == 2;
	}
