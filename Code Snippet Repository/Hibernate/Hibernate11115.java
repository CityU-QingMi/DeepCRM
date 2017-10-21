	@Test
	@TestForIssue(jiraKey = "")
	public void testForEntitiesAtRevisionNotAuditedSingleResult() {
		try {
			getAuditReader().createQuery()
					.forEntitiesAtRevision( NonAuditedEntity.class, 1 )
					.setMaxResults( 1 )
					.getSingleResult();
			fail( "Expected a NotAuditedException" );
		}
		catch ( Exception e ) {
			assertTyping( NotAuditedException.class, e );
		}
	}
