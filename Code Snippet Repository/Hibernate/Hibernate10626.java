	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		id1 = 1;

		// Rev 1
		em.getTransaction().begin();
		EmptyChildEntity pe = new EmptyChildEntity( id1, "x" );
		em.persist( pe );
		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();
		pe = em.find( EmptyChildEntity.class, id1 );
		pe.setData( "y" );
		em.getTransaction().commit();
	}
