	@Test
	public void testEnversCompatibility() throws Exception {
		// revision 1
		userTransaction.begin();
		entityManager.joinTransaction();
		AuditedEntity entity = new AuditedEntity( 1, "Marco Polo" );
		entityManager.persist( entity );
		userTransaction.commit();

		// revision 2
		userTransaction.begin();
		entityManager.joinTransaction();
		entity.setName( "George Washington" );
		entityManager.merge( entity );
		userTransaction.commit();

		entityManager.clear();

		// verify audit history revision counts
		userTransaction.begin();
		final AuditReader auditReader = AuditReaderFactory.get( entityManager );
		assertEquals( Arrays.asList( 1, 2 ), auditReader.getRevisions( AuditedEntity.class, 1 ) );
		userTransaction.commit();
	}
