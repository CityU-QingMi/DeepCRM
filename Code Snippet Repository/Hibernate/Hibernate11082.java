	@Test
	@TestForIssue(jiraKey = "")
	public void testEntityIdModifiedFlagNotSupported() {
		try {
			getAuditReader().createQuery().forRevisionsOfEntity( IntTestEntity.class, true, true )
					.add( AuditEntity.id().hasChanged() )
					.getResultList();
		}
		catch (UnsupportedOperationException e1) {
			try {
				getAuditReader().createQuery().forRevisionsOfEntity( IntTestEntity.class, true, true )
						.add( AuditEntity.id().hasNotChanged() )
						.getResultList();
			}
			catch (UnsupportedOperationException e2) {
				return;
			}
			Assert.fail();
		}
		Assert.fail();
	}
