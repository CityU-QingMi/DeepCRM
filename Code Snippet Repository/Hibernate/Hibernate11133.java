	@Test
	@TestForIssue(jiraKey = "")
	public void testBetweenInsideDisjunction() {
		List result = getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, true, true )
				.add(
						AuditEntity.disjunction()
								.add( AuditEntity.property( "number" ).between( 0, 5 ) )
								.add( AuditEntity.property( "number" ).between( 20, 100 ) )
				)
				.getResultList();

		for ( Object o : result ) {
			StrIntTestEntity entity = (StrIntTestEntity) o;
			int number = entity.getNumber();
			Assert.assertTrue( (number >= 0 && number <= 5) || (number >= 20 && number <= 100) );
		}
	}
