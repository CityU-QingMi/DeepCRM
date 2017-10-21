	@Test
	@SuppressWarnings("")
	public void testIt() throws Exception {
		count = 0;

		utx.begin();
		EntityManager em = emf.createEntityManager();
		em.persist( new MyEntity( 1 ) );
		utx.commit();

		assertEquals( 1, count );

		utx.begin();
		em = emf.createEntityManager();
		MyEntity it = em.find( MyEntity.class, 1 );
		assertNotNull( it );
		em.remove( it );
		utx.commit();
	}
