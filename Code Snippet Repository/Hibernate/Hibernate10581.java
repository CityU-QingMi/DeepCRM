	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		StrTestEntity fe = new StrTestEntity( "x" );
		em.persist( fe );
		em.flush();

		em.getTransaction().commit();

		// No revision - we change the data, but do not flush the session
		em.getTransaction().begin();

		fe = em.find( StrTestEntity.class, fe.getId() );
		fe.setStr( "y" );

		em.getTransaction().commit();

		// Revision 2 - only the first change should be saved
		em.getTransaction().begin();

		fe = em.find( StrTestEntity.class, fe.getId() );
		fe.setStr( "z" );
		em.flush();

		fe = em.find( StrTestEntity.class, fe.getId() );
		fe.setStr( "z2" );

		em.getTransaction().commit();

		//

		id = fe.getId();
	}
