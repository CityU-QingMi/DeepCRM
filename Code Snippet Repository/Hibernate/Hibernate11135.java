	@Test
	@TestForIssue(jiraKey = "")
	public void testIlikeWithMatchMode() {
		StrIntTestEntity site1 = new StrIntTestEntity( "aBc", 10, id1 );
		
		StrIntTestEntity result = (StrIntTestEntity) getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, true, true )
				.add( AuditEntity.property( "str1" ).ilike( "BC", MatchMode.ANYWHERE ) )
				.getSingleResult();
		
		Assert.assertEquals( site1, result );
	}
