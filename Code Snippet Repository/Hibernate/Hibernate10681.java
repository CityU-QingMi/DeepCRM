	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		ComponentTestEntity cte1 = new ComponentTestEntity( new Component1( "a" ) );

		em.persist( cte1 );

		em.getTransaction().commit();

		// Revision 2
		em = getEntityManager();
		em.getTransaction().begin();

		cte1 = em.find( ComponentTestEntity.class, cte1.getId() );

		cte1.setComp1( new Component1( "b" ) );

		em.getTransaction().commit();

		// Revision 3
		em = getEntityManager();
		em.getTransaction().begin();

		cte1 = em.find( ComponentTestEntity.class, cte1.getId() );

		cte1.getComp1().setData( "c" );

		em.getTransaction().commit();

		id1 = cte1.getId();
	}
