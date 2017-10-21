	@Test
	@TestForIssue(jiraKey = "")
	public void testEntityIdRestriction() {
		List<IntTestEntity> list = getAuditReader().createQuery().forRevisionsOfEntity(
				IntTestEntity.class,
				true,
				true
		)
				.add( AuditEntity.id().between( 2, 3 ) )
				.getResultList();
		Assert.assertTrue(
				TestTools.checkCollection(
						list,
						new IntTestEntity( 10, 2 ), new IntTestEntity( 8, 3 ), new IntTestEntity( 52, 2 )
				)
		);
	}
