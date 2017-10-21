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

		// Revision 2
		em.getTransaction().begin();

		fe = em.find( StrTestEntity.class, fe.getId() );

		fe.setStr( "y" );
		em.flush();

		em.remove( em.find( StrTestEntity.class, fe.getId() ) );
		em.flush();

		em.getTransaction().commit();

		//

		id = fe.getId();
	}
