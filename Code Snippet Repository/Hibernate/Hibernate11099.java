	@Test
	public void testRevisionsOfEntityWithoutDelete() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, false )
				.add( AuditEntity.id().eq( id2 ) )
				.getResultList();

		assert result.size() == 1;

		assert ((Object[]) result.get( 0 ))[0].equals( new StrIntTestEntity( "b", 11, id2 ) );
		assert ((SequenceIdRevisionEntity) ((Object[]) result.get( 0 ))[1]).getId() == 1;
		assert ((Object[]) result.get( 0 ))[2].equals( RevisionType.ADD );
	}
