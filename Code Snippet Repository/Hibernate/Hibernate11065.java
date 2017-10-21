	@Test
	public void testTernaryMap() {
		final TernaryMapEntity ternaryMap = new TernaryMapEntity();
		ternaryMap.setId( ternaryMapId );
		ternaryMap.getMap().put( intEntity1, stringEntity1 );
		ternaryMap.getMap().put( new IntTestPrivSeqEntity( 2, intEntity2.getId() ) , new StrTestPrivSeqEntity( "Value 2", stringEntity2.getId() ) );

		TernaryMapEntity entity = getAuditReader().find( TernaryMapEntity.class, ternaryMapId, 15 );

		Assert.assertEquals( ternaryMap.getMap(), entity.getMap() );

		ternaryMap.getMap().clear();
		ternaryMap.getMap().put( intEntity1, stringEntity1 );
		ternaryMap.getMap().put( intEntity2, stringEntity2 );

		entity = getAuditReader().find( TernaryMapEntity.class, ternaryMapId, 16 );

		Assert.assertEquals( ternaryMap.getMap(), entity.getMap() );

		List queryResult = getAuditReader().createQuery().forRevisionsOfEntity( TernaryMapEntity.class, false, true )
				.add( AuditEntity.id().eq( ternaryMapId ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 17, getRevisionNumber( objArray[1] ) );

		entity = (TernaryMapEntity) objArray[0];
		Assert.assertEquals( ternaryMap.getMap(), entity.getMap() );
	}
