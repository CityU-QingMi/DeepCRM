	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		IntTestEntity ite1 = new IntTestEntity( 12 );
		IntTestEntity ite2 = new IntTestEntity( 5 );
		IntTestEntity ite3 = new IntTestEntity( 8 );
		IntTestEntity ite4 = new IntTestEntity( 1 );

		em.persist( ite1 );
		em.persist( ite2 );
		em.persist( ite3 );
		em.persist( ite4 );

		id1 = ite1.getId();
		id2 = ite2.getId();
		id3 = ite3.getId();
		id4 = ite4.getId();

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		IntTestEntity ite5 = new IntTestEntity( 3 );
		em.persist( ite5 );
		id5 = ite5.getId();

		ite1 = em.find( IntTestEntity.class, id1 );
		ite1.setNumber( 0 );

		ite4 = em.find( IntTestEntity.class, id4 );
		ite4.setNumber( 15 );

		em.getTransaction().commit();
	}
