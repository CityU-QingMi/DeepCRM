	@Test
	public void testMergeWhenExtended() throws Exception {
		Book book = new Book();
		book.name = "Le petit prince";
		EntityManager em = getOrCreateEntityManager();
		Statistics stats = ( ( HibernateEntityManagerFactory ) entityManagerFactory() ).getSessionFactory().getStatistics();

		em.getTransaction().begin();
		em.persist( book );
		assertEquals( 0, stats.getEntityInsertCount() );
		em.getTransaction().commit();

		em.clear(); //persist and clear
		stats.clear();
		stats.setStatisticsEnabled( true );

		Book bookReloaded = em.find( Book.class, book.id );

		book.name = "Le prince";
		assertEquals( "Merge should use the available entiies in the PC", em.merge( book ), bookReloaded );
		assertEquals( book.name, bookReloaded.name );

		assertEquals( 0, stats.getEntityDeleteCount() );
		assertEquals( 0, stats.getEntityInsertCount() );
		assertEquals( "Updates should have been queued", 0, stats.getEntityUpdateCount() );

		em.getTransaction().begin();
		Book bookReReloaded = em.find( Book.class, bookReloaded.id );
		assertEquals( "reload should return the object in PC", bookReReloaded, bookReloaded );
		assertEquals( bookReReloaded.name, bookReloaded.name );
		em.getTransaction().commit();

		assertEquals( 0, stats.getEntityDeleteCount() );
		assertEquals( 0, stats.getEntityInsertCount() );
		assertEquals( "Work on Tx should flush", 1, stats.getEntityUpdateCount() );

		em.getTransaction().begin();
		em.remove( bookReReloaded );
		em.getTransaction().commit();

		em.close();
		stats.setStatisticsEnabled( false );
	}
