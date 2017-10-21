	@Test
	@TestForIssue(jiraKey = "")
	public void testMaximizeInDisjunction() {
		List<Integer> queryIds = Arrays.asList( id2, id3 );

		AuditDisjunction disjunction = AuditEntity.disjunction();
		for ( Integer id : queryIds ) {
			AuditCriterion crit = AuditEntity.revisionNumber().maximize()
					.add( AuditEntity.id().eq( id ) )
					.add( AuditEntity.revisionType().ne( RevisionType.DEL ) );
			disjunction.add( crit );
			// Workaround: using this line instead works correctly:
			// disjunction.add(AuditEntity.conjunction().add(crit));
		}

		List<?> beforeDeletionRevisions = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, false, false )
				.add( disjunction )
				.addOrder( AuditEntity.property( "id" ).asc() )
				.getResultList();

		Assert.assertEquals( 2, beforeDeletionRevisions.size() );

		Object[] result1 = (Object[]) beforeDeletionRevisions.get( 0 );
		Object[] result2 = (Object[]) beforeDeletionRevisions.get( 1 );

		Assert.assertEquals( new StrIntTestEntity( "b", 20, id2 ), result1[0] );
		// Making sure that we have received an entity added at revision 3.
		Assert.assertEquals( 3, ((SequenceIdRevisionEntity) result1[1]).getId() );
		Assert.assertEquals( RevisionType.ADD, result1[2] );
		Assert.assertEquals( new StrIntTestEntity( "c", 30, id3 ), result2[0] );
		// Making sure that we have received an entity added at revision 3.
		Assert.assertEquals( 3, ((SequenceIdRevisionEntity) result2[1]).getId() );
		Assert.assertEquals( RevisionType.ADD, result2[2] );
	}
