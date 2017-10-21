	@Test
	@TestForIssue(jiraKey = "")
	public void testEntityIdOrdering() {
		List<IntTestEntity> list = getAuditReader().createQuery().forRevisionsOfEntity(
				IntTestEntity.class,
				true,
				true
		)
				.add( AuditEntity.revisionNumber().lt( 2 ) )
				.addOrder( AuditEntity.id().desc() )
				.getResultList();
		Assert.assertEquals( Arrays.asList( new IntTestEntity( 10, 2 ), new IntTestEntity( 2, 1 ) ), list );
	}
