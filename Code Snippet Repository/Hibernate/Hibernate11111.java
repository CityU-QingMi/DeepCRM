	@Test
	@TestForIssue(jiraKey = "")
	public void testAllLatestRevisionsOfEntityType() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, true )
				.add( AuditEntity.revisionNumber().maximize().computeAggregationInInstanceContext() )
				.addOrder( AuditEntity.property( "id" ).asc() )
				.getResultList();

		Assert.assertEquals( 4, result.size() );

		Object[] result1 = (Object[]) result.get( 0 );
		Object[] result2 = (Object[]) result.get( 1 );
		Object[] result3 = (Object[]) result.get( 2 );
		Object[] result4 = (Object[]) result.get( 3 );

		checkRevisionData( result1, 4, RevisionType.MOD, new StrIntTestEntity( "d", 5, id1 ) );
		checkRevisionData( result2, 4, RevisionType.MOD, new StrIntTestEntity( "a", 20, id2 ) );
		checkRevisionData( result3, 1, RevisionType.ADD, new StrIntTestEntity( "c", 42, id3 ) );
		checkRevisionData( result4, 5, RevisionType.DEL, new StrIntTestEntity( null, null, id4 ) );
	}
