	@Test
	@TestForIssue(jiraKey = "")
	public void testMultipleIdPropertyRestriction() {
		MulIdTestEntity ver2 = (MulIdTestEntity) getAuditReader().createQuery()
				.forEntitiesAtRevision( MulIdTestEntity.class, 2 )
				.add( AuditEntity.property( "id1" ).eq( mulId1.getId1() ) )
				.add( AuditEntity.property( "id2" ).eq( mulId1.getId2() ) )
				.getSingleResult();

		Assert.assertEquals( new MulIdTestEntity( mulId1.getId1(), mulId1.getId2(), "data" ), ver2 );
	}
