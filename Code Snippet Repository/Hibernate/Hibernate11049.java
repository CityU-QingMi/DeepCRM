	@Test
	@Priority(10)
	public void initData() {
		ei = new EmbId( 1, 2 );

		EntityManager em = getEntityManager();

		// Revision 1
		EmbIdTestEntity eite = new EmbIdTestEntity( ei, "data" );
		UniRefIngMulIdEntity notNullRef = new UniRefIngMulIdEntity( 1, "data 1", eite );
		UniRefIngMulIdEntity nullRef = new UniRefIngMulIdEntity( 2, "data 2", null );

		em.getTransaction().begin();
		em.persist( eite );
		em.persist( notNullRef );
		em.persist( nullRef );
		em.getTransaction().commit();
	}
