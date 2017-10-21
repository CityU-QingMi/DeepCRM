	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		BasicTestEntity1 bte1 = new BasicTestEntity1( "x", 1 );
		BasicTestEntity1 bte2 = new BasicTestEntity1( "y", 20 );
		em.persist( bte1 );
		em.persist( bte2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		bte1 = em.find( BasicTestEntity1.class, bte1.getId() );
		bte2 = em.find( BasicTestEntity1.class, bte2.getId() );
		BasicTestEntity1 bte3 = new BasicTestEntity1( "z", 300 );
		bte1.setStr1( "x2" );
		bte2.setLong1( 21 );
		em.persist( bte3 );

		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();

		bte2 = em.find( BasicTestEntity1.class, bte2.getId() );
		bte3 = em.find( BasicTestEntity1.class, bte3.getId() );
		bte2.setStr1( "y3" );
		bte2.setLong1( 22 );
		bte3.setStr1( "z3" );

		em.getTransaction().commit();

		id1 = bte1.getId();
		id2 = bte2.getId();
		id3 = bte3.getId();
	}
