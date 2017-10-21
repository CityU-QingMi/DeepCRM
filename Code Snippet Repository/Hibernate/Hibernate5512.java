	@Test
	public void testSetProperty() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Wallet wallet = new Wallet();
		wallet.setSerial( "000" );
		em.persist( wallet );
		em.getTransaction().commit();

		em.clear();
		assertEquals( em.getProperties().get( AvailableSettings.FLUSH_MODE ), "AUTO" );
		assertNotNull(
				"With default settings the entity should be persisted on commit.",
				em.find( Wallet.class, wallet.getSerial() )
		);

		em.getTransaction().begin();
		wallet = em.merge( wallet );
		em.remove( wallet );
		em.getTransaction().commit();

		em.clear();
		assertNull( "The entity should have been removed.", em.find( Wallet.class, wallet.getSerial() ) );

		em.setProperty( "org.hibernate.flushMode", "MANUAL" +
				"" );
		em.getTransaction().begin();
		wallet = new Wallet();
		wallet.setSerial( "000" );
		em.persist( wallet );
		em.getTransaction().commit();

		em.clear();
		assertNull(
				"With a flush mode of manual the entity should not have been persisted.",
				em.find( Wallet.class, wallet.getSerial() )
		);
		assertEquals( "MANUAL", em.getProperties().get( AvailableSettings.FLUSH_MODE ) );
		em.close();
	}
