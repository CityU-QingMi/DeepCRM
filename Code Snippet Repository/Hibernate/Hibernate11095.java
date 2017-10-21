	@Test
	public void testRevisionsOfId2Query() {
		List<Object[]> result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.add( AuditEntity.id().eq( id2 ) )
				.getResultList();

		assert result.get( 0 )[0].equals( new StrIntTestEntity( "b", 15, id2 ) );
		assert result.get( 0 )[1] instanceof CustomRevEntity;
		assert ((CustomRevEntity) result.get( 0 )[1]).getCustomId() == 1;
	}
