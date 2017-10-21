	@Test
	@TestForIssue(jiraKey = "")
	public void testIdPropertyRestriction() {
		StrIntTestEntity ver2 = (StrIntTestEntity) getAuditReader().createQuery()
				.forEntitiesAtRevision( StrIntTestEntity.class, 2 )
				.add( AuditEntity.property( "id" ).eq( id2 ) )
				.getSingleResult();

		Assert.assertEquals( new StrIntTestEntity( "a", 20, id2 ), ver2 );
	}
