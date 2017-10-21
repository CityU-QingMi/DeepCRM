	@Test
	public void testMultipleConstructorResultNativeQuery() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( new Person( 1, "John", new Date() ) );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		List results = em.createNamedQuery( "person-id-and-name2" ).getResultList();
		assertEquals( 1, results.size() );
		Object[] result = assertTyping( Object[].class, results.get( 0 ) );
		assertEquals( 2, result.length );
		assertTyping( Person.class, result[0] );
		assertTyping( Person.class, result[1] );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete from Person" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
