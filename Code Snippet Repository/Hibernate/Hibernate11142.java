	@Test
	public void testSelectEntitiesAndRevisionsQuery() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.add( AuditEntity.id().eq( id1 ) )
				.getResultList();

		assert result.size() == 3;

		assert ((Object[]) result.get( 0 ))[0].equals( new StrIntTestEntity( "a", 10, id1 ) );
		assert ((Object[]) result.get( 1 ))[0].equals( new StrIntTestEntity( "aBc", 10, id1 ) );
		assert ((Object[]) result.get( 2 ))[0].equals( new StrIntTestEntity( null, null, id1 ) );

		assert ((SequenceIdRevisionEntity) ((Object[]) result.get( 0 ))[1]).getId() == 1;
		assert ((SequenceIdRevisionEntity) ((Object[]) result.get( 1 ))[1]).getId() == 2;
		assert ((SequenceIdRevisionEntity) ((Object[]) result.get( 2 ))[1]).getId() == 4;

		assert ((Object[]) result.get( 0 ))[2].equals( RevisionType.ADD );
		assert ((Object[]) result.get( 1 ))[2].equals( RevisionType.MOD );
		assert ((Object[]) result.get( 2 ))[2].equals( RevisionType.DEL );
	}
