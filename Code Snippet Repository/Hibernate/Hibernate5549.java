	@Test
	public void testPostPersist() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		Cat c = new Cat();
		em.getTransaction().begin();
		c.setLength( 23 );
		c.setAge( 2 );
		c.setName( "Beetle" );
		c.setDateOfBirth( new Date() );
		em.persist( c );
		em.getTransaction().commit();
		em.close();
		List ids = Cat.getIdList();
		Object id = Cat.getIdList().get( ids.size() - 1 );
		assertNotNull( id );
	}
