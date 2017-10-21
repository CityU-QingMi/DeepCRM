	@Test
	@TestForIssue(jiraKey = "")
	public void testForEntitiesAtRevisionNotAuditedMultipleResults() {
		try {
			getAuditReader().createQuery()
					.forEntitiesAtRevision( NonAuditedEntity.class, 1 )
					.getResultList();
			fail( "Expected a NotAuditedException" );
		}
		catch ( Exception e ) {
			assertTyping( NotAuditedException.class, e );
		}
	}
