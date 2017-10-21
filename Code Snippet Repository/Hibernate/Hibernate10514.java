	@Test
	public void testEmbeddableThatExtendsMappedSuperclass() {

		// Reload and Compare Revision
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		EntityWithEmbeddableWithNoDeclaredData entityLoaded = em.find( EntityWithEmbeddableWithNoDeclaredData.class, id );

		AuditReader reader = AuditReaderFactory.get( em );

		List<Number> revs = reader.getRevisions( EntityWithEmbeddableWithNoDeclaredData.class, id );
		Assert.assertEquals( 1, revs.size() );

		EntityWithEmbeddableWithNoDeclaredData entityRev1 = reader.find( EntityWithEmbeddableWithNoDeclaredData.class, id, revs.get( 0 ) );

		em.getTransaction().commit();
		Assert.assertEquals( entityLoaded.getName(), entityRev1.getName() );

		// value should be null because there is no data in EmbeddableWithNoDeclaredData
		// and the fields in AbstractEmbeddable should not be audited.
		Assert.assertNull( entityRev1.getValue() );
	}
