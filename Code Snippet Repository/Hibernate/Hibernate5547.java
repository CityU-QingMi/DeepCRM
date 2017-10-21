	@Test
	public void testCallbackMethod() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		Cat c = new Cat();
		c.setName( "Kitty" );
		c.setDateOfBirth( new Date( 90, 11, 15 ) );
		em.getTransaction().begin();
		em.persist( c );
		em.getTransaction().commit();
		em.clear();
		em.getTransaction().begin();
		c = em.find( Cat.class, c.getId() );
		assertFalse( c.getAge() == 0 );
		c.setName( "Tomcat" ); //update this entity
		em.getTransaction().commit();
		em.clear();
		em.getTransaction().begin();
		c = em.find( Cat.class, c.getId() );
		assertEquals( "Tomcat", c.getName() );
		em.getTransaction().commit();
		em.close();
	}
