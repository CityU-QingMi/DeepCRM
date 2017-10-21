	@Test
	@TestForIssue(jiraKey = "")
	public void testRevisionsOfEntityNotAuditedSingleResult() {
		try {
			getAuditReader().createQuery()
					.forRevisionsOfEntity( NonAuditedEntity.class, false, false )
					.setMaxResults( 1 )
					.getSingleResult();
			fail( "Expected a NotAuditedException" );
		}
		catch ( Exception e ) {
			assertTyping( NotAuditedException.class, e );
		}
	}
