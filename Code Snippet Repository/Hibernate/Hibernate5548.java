	@Test
	public void testEntityListener() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		Cat c = new Cat();
		c.setName( "Kitty" );
		c.setLength( 12 );
		c.setDateOfBirth( new Date( 90, 11, 15 ) );
		em.getTransaction().begin();
		int previousVersion = c.getManualVersion();
		em.persist( c );
		em.getTransaction().commit();
		em.getTransaction().begin();
		c = em.find( Cat.class, c.getId() );
		assertNotNull( c.getLastUpdate() );
		assertTrue( previousVersion < c.getManualVersion() );
		assertEquals( 12, c.getLength() );
		previousVersion = c.getManualVersion();
		c.setName( "new name" );
		em.getTransaction().commit();
		em.getTransaction().begin();
		c = em.find( Cat.class, c.getId() );
		assertTrue( previousVersion < c.getManualVersion() );
		em.getTransaction().commit();

		em.close();
	}
