	@Test
	@TestForIssue(jiraKey = "")
	public void testForEntitiesModifiedAtRevisionNotAuditedSingleResult() {
		try {
			getAuditReader().createQuery()
					.forEntitiesModifiedAtRevision( NonAuditedEntity.class, 1 )
					.setMaxResults( 1 )
					.getSingleResult();
			fail( "Expected a NotAuditedException" );
		}
		catch ( Exception e ) {
			assertTyping( NotAuditedException.class, e );
		}
	}
