	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		id1 = 1;

		// Rev 1
		em.getTransaction().begin();
		ChildEntity ce = new ChildEntity( id1, "x", null );
		em.persist( ce );
		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();
		ce = em.find( ChildEntity.class, id1 );
		ce.setData( null );
		ce.setNumVal( 2l );
		em.getTransaction().commit();
	}
