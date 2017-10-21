	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		GreetingSetPO set1 = new GreetingSetPO();
		set1.setName( "a1" );

		GreetingSetPO set2 = new GreetingSetPO();
		set2.setName( "a2" );

		// Revision 1
		em.getTransaction().begin();

		em.persist( set1 );
		em.persist( set2 );

		set1_id = set1.getId();
		set2_id = set2.getId();

		em.getTransaction().commit();
		em.clear();

		// Revision 2
		em.getTransaction().begin();

		GreetingPO g1 = new GreetingPO();
		g1.setGreeting( "g1" );
		g1.setGreetingSet( em.getReference( GreetingSetPO.class, set1_id ) );

		em.persist( g1 );
		g1_id = g1.getId();

		em.getTransaction().commit();
		em.clear();

		// Revision 3
		em.getTransaction().begin();

		g1 = em.find( GreetingPO.class, g1_id );

		g1.setGreetingSet( em.getReference( GreetingSetPO.class, set2_id ) );

		em.getTransaction().commit();
	}
