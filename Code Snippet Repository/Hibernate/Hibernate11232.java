	@Test
	@Priority(10)
	public void initData() {
		id = new MulId( 1, 2 );

		SecondaryMulIdTestEntity ste = new SecondaryMulIdTestEntity( id, "a", "1" );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		em.persist( ste );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ste = em.find( SecondaryMulIdTestEntity.class, id );
		ste.setS1( "b" );
		ste.setS2( "2" );

		em.getTransaction().commit();
	}
