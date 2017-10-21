	@Test
	public void testTwoLevelDeepPersist() throws Exception {
		EntityTransaction tx;

		EntityManager em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Conference jbwBarcelona = new Conference();
		jbwBarcelona.setDate( new Date() );
		ExtractionDocumentInfo info = new ExtractionDocumentInfo();
		info.setConference( jbwBarcelona );
		jbwBarcelona.setExtractionDocument( info );
		info.setLastModified( new Date() );
		ExtractionDocument doc = new ExtractionDocument();
		doc.setDocumentInfo( info );
		info.setDocuments( new ArrayList<ExtractionDocument>() );
		info.getDocuments().add( doc );
		doc.setBody( new byte[]{'c', 'f'} );
		em.persist( jbwBarcelona );
		tx.commit();
		em.close();

		em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		jbwBarcelona = em.find( Conference.class, jbwBarcelona.getId() );
		assertTrue( Hibernate.isInitialized( jbwBarcelona ) );
		assertTrue( Hibernate.isInitialized( jbwBarcelona.getExtractionDocument() ) );
		assertFalse( Hibernate.isInitialized( jbwBarcelona.getExtractionDocument().getDocuments() ) );
		em.flush();
		assertTrue( Hibernate.isInitialized( jbwBarcelona ) );
		assertTrue( Hibernate.isInitialized( jbwBarcelona.getExtractionDocument() ) );
		assertFalse( Hibernate.isInitialized( jbwBarcelona.getExtractionDocument().getDocuments() ) );
		em.remove( jbwBarcelona );
		tx.commit();
		em.close();
	}
