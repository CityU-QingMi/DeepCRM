	@Test
	@TestForIssue(jiraKey = "")
	public void testRevisionsOfEntityNotAuditedMultipleResults() {
		try {
			getAuditReader().createQuery()
					.forRevisionsOfEntity( NonAuditedEntity.class, false, false )
					.getResultList();
			fail( "Expected a NotAuditedException" );
		}
		catch ( Exception e ) {
			assertTyping( NotAuditedException.class, e );
		}
	}
