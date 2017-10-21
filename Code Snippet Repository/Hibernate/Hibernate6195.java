	@Test
	public void testDefaultEventListener() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CounterListener.reset();

		Employee e = new Employee();
        e.setId(Long.valueOf(100));
        e.setName("Bubba");
        e.setHomeAddress(new Address("123 Main St", "New York", "NY", "11111"));
        e.setMailAddress(new Address("P.O. Box 123", "New York", "NY", "11111"));

        em.persist(e);

		em.flush();

		em.clear();

		em.find( Employee.class, e.getId() ).setName( "Bibo" );

		em.flush();

		em.clear();

		em.remove( em.find( Employee.class, e.getId() ) );

		em.flush();


		em.getTransaction().rollback();
		em.close();

		assertEquals( 1, CounterListener.insert );
		assertEquals( 1, CounterListener.update );
		assertEquals( 1, CounterListener.delete );
	}
