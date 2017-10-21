	@Test
	@TestForIssue( jiraKey = "" )
	public void testVersionAttributeMetadata() {
		EntityManager em = getOrCreateEntityManager();
		EntityType<VersionedEntity> metadata = em.getMetamodel().entity( VersionedEntity.class );
		assertNotNull( metadata.getDeclaredVersion( int.class ) );
		assertTrue( metadata.getDeclaredVersion( int.class ).isVersion() );
		assertEquals( 3, metadata.getDeclaredSingularAttributes().size() );
		assertTrue( metadata.getDeclaredSingularAttributes().contains( metadata.getDeclaredVersion( int.class ) ) );
		em.close();
	}
