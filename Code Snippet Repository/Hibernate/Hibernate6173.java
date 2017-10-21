	@Test
	public void testTransactionalOperationsWhenExtended() throws Exception {
		Book book = new Book();
		book.name = "Le petit prince";
		EntityManager em = getOrCreateEntityManager();
		Statistics stats = ( ( HibernateEntityManagerFactory ) entityManagerFactory() ).getSessionFactory().getStatistics();
		stats.clear();
		stats.setStatisticsEnabled( true );

		em.persist( book );
		assertEquals( 0, stats.getEntityInsertCount() );
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
		assertEquals( 1, stats.getEntityInsertCount() );

		em.clear();
		book.name = "Le prince";
		book = em.merge( book );

		em.refresh( book );
		assertEquals( 0, stats.getEntityUpdateCount() );
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
		assertEquals( 0, stats.getEntityUpdateCount() );

		book.name = "Le prince";
		em.getTransaction().begin();
		em.find( Book.class, book.id );
		em.getTransaction().commit();
		assertEquals( 1, stats.getEntityUpdateCount() );

		em.remove( book );
		assertEquals( 0, stats.getEntityDeleteCount() );
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
		assertEquals( 1, stats.getEntityDeleteCount() );

		em.close();
		stats.setStatisticsEnabled( false );
	}
