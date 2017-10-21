	@Test
	@TestForIssue(jiraKey = "")
	public void testMaximizeInDisjunction() {
		List<Integer> idsToQuery = Arrays.asList( id1, id3 );

		AuditDisjunction disjunction = AuditEntity.disjunction();

		for ( Integer id : idsToQuery ) {
			disjunction.add( AuditEntity.revisionNumber().maximize().add( AuditEntity.id().eq( id ) ) );
		}
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, true, true )
				.add( disjunction )
				.getResultList();

		Set<Integer> idsSeen = new HashSet<Integer>();
		for ( Object o : result ) {
			StrIntTestEntity entity = (StrIntTestEntity) o;
			Integer id = entity.getId();
			Assert.assertTrue( "Entity with ID " + id + " returned but not queried for.", idsToQuery.contains( id ) );
			if ( !idsSeen.add( id ) ) {
				Assert.fail( "Multiple revisions returned with ID " + id + "; expected only one." );
			}
		}
	}
