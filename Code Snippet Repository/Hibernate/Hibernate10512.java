	@Test
	@FailureExpected( jiraKey = "" )
	public void testEmbeddableThatExtendsMappedSuperclass() {

		// Reload and Compare Revision
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		EntityWithEmbeddableWithDeclaredData entityLoaded = em.find( EntityWithEmbeddableWithDeclaredData.class, id );

		AuditReader reader = AuditReaderFactory.get( em );

		List<Number> revs = reader.getRevisions( EntityWithEmbeddableWithDeclaredData.class, id );
		Assert.assertEquals( 1, revs.size() );

		EntityWithEmbeddableWithDeclaredData entityRev1 = reader.find( EntityWithEmbeddableWithDeclaredData.class, id, revs.get( 0 ) );
		em.getTransaction().commit();
		Assert.assertEquals( entityLoaded.getName(), entityRev1.getName() );

		// only value.codeArt should be audited because it is the only audited field in EmbeddableWithDeclaredData;
		// fields in AbstractEmbeddable should not be audited.
		Assert.assertEquals( entityLoaded.getValue().getCodeart(), entityRev1.getValue().getCodeart() );
		Assert.assertNull( entityRev1.getValue().getCode() );
	}
