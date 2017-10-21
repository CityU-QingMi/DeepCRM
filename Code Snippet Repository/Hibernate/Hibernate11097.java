	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		StrIntTestEntity site1 = new StrIntTestEntity( "a", 10 );
		StrIntTestEntity site2 = new StrIntTestEntity( "b", 11 );

		em.persist( site1 );
		em.persist( site2 );

		id2 = site2.getId();

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		site2 = em.find( StrIntTestEntity.class, id2 );
		em.remove( site2 );

		em.getTransaction().commit();
	}
