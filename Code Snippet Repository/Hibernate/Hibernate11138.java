	@Test
	@TestForIssue(jiraKey = "")
	public void testEmbeddedIdPropertyRestriction() {
		EmbIdTestEntity ver2 = (EmbIdTestEntity) getAuditReader().createQuery()
				.forEntitiesAtRevision( EmbIdTestEntity.class, 2 )
				.add( AuditEntity.property( "id.x" ).eq( embId1.getX() ) )
				.add( AuditEntity.property( "id.y" ).eq( embId1.getY() ) )
				.getSingleResult();

		Assert.assertEquals( new EmbIdTestEntity( embId1, "something" ), ver2 );
	}
