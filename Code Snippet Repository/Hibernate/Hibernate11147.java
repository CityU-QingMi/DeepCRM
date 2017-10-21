	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		StrIntTestEntity site1 = new StrIntTestEntity( "a", 10 );
		em.persist( site1 );
		id1 = site1.getId();
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		em.remove( site1 );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		StrIntTestEntity site2 = new StrIntTestEntity( "b", 20 );
		em.persist( site2 );
		id2 = site2.getId();
		StrIntTestEntity site3 = new StrIntTestEntity( "c", 30 );
		em.persist( site3 );
		id3 = site3.getId();
		em.getTransaction().commit();

		// Revision 4
		em.getTransaction().begin();
		em.remove( site2 );
		em.remove( site3 );
		em.getTransaction().commit();

		em.close();
	}
