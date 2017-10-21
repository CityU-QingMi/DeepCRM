	@Test
	@TestForIssue(jiraKey = "")
	public void testForEntitiesModifiedAtRevisionNotAuditedMultipleResults() {
		try {
			getAuditReader().createQuery()
					.forEntitiesModifiedAtRevision( NonAuditedEntity.class, 1 )
					.getResultList();
			fail( "Expected a NotAuditedException" );
		}
		catch ( Exception e ) {
			assertTyping( NotAuditedException.class, e );
		}
	}
