	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		IntTestEntity ite1 = new IntTestEntity( 2 );
		IntTestEntity ite2 = new IntTestEntity( 10 );
		em.persist( ite1 );
		em.persist( ite2 );
		Integer id1 = ite1.getId();
		Integer id2 = ite2.getId();
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		IntTestEntity ite3 = new IntTestEntity( 8 );
		UnusualIdNamingEntity uine1 = new UnusualIdNamingEntity( "id1", "data1" );
		em.persist( uine1 );
		em.persist( ite3 );
		ite1 = em.find( IntTestEntity.class, id1 );
		ite1.setNumber( 0 );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		ite2 = em.find( IntTestEntity.class, id2 );
		ite2.setNumber( 52 );
		em.getTransaction().commit();

		em.close();
	}
