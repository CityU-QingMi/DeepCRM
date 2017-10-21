	@Test
	@TestForIssue(jiraKey = "")
	public void testIlike() {
		StrIntTestEntity site1 = new StrIntTestEntity( "aBc", 10, id1 );
		
		StrIntTestEntity result = (StrIntTestEntity) getAuditReader().createQuery()
				.forRevisionsOfEntity( StrIntTestEntity.class, true, true )
				.add( AuditEntity.property( "str1" ).ilike( "abc" ) )
				.getSingleResult();
		
		Assert.assertEquals( site1, result );
	}
