	@Test
	@Priority(10)
	public void initData() {
		id = new EmbId( 1, 2 );

		SecondaryEmbIdTestEntity ste = new SecondaryEmbIdTestEntity( id, "a", "1" );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		em.persist( ste );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ste = em.find( SecondaryEmbIdTestEntity.class, ste.getId() );
		ste.setS1( "b" );
		ste.setS2( "2" );

		em.getTransaction().commit();
	}
