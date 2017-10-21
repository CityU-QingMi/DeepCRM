	@Test
	@Priority(10)
	public void initData() {
		NamingTestEntity1 nte1 = new NamingTestEntity1( "data1" );
		NamingTestEntity1 nte2 = new NamingTestEntity1( "data2" );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		em.persist( nte1 );
		em.persist( nte2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		nte1 = em.find( NamingTestEntity1.class, nte1.getId() );
		nte1.setData( "data1'" );

		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();

		nte2 = em.find( NamingTestEntity1.class, nte2.getId() );
		nte2.setData( "data2'" );

		em.getTransaction().commit();

		//

		id1 = nte1.getId();
		id2 = nte2.getId();
	}
