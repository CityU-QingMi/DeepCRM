	@Test
	public void testCreateThenDrop() throws Exception {
		URL persistenceXmlUrl = Thread.currentThread().getContextClassLoader().getResource( PERSISTENCE_XML_RESOURCE_NAME );
		if ( persistenceXmlUrl == null ) {
			persistenceXmlUrl = Thread.currentThread().getContextClassLoader().getResource( '/' + PERSISTENCE_XML_RESOURCE_NAME );
		}

		assertNotNull( persistenceXmlUrl );

		ParsedPersistenceXmlDescriptor persistenceUnit = PersistenceXmlParser.locateIndividualPersistenceUnit( persistenceXmlUrl );
		// creating the EMF causes SchemaCreator to be run...
		EntityManagerFactory emf = Bootstrap.getEntityManagerFactoryBuilder( persistenceUnit, Collections.emptyMap() ).build();

		// closing the EMF causes the delayed SchemaDropper to be run...
		//		wrap in a transaction just to see if we can get this to fail in the way the WF report says;
		//		in my experience however this succeeds with or without the transaction
		final TransactionManager tm = emf.unwrap( SessionFactoryImplementor.class ).getServiceRegistry().getService( JtaPlatform.class ).retrieveTransactionManager();

		tm.begin();
		Transaction txn = tm.getTransaction();
		emf.close();
		txn.commit();
	}
