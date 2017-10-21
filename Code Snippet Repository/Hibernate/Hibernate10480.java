	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		ComponentSetTestEntity cte1 = new ComponentSetTestEntity();

		ComponentSetTestEntity cte2 = new ComponentSetTestEntity();
		cte2.getComps().add( new Component1( "string1", null ) );

		em.persist( cte2 );
		em.persist( cte1 );

		em.getTransaction().commit();

		// Revision 2
		em = getEntityManager();
		em.getTransaction().begin();

		cte1 = em.find( ComponentSetTestEntity.class, cte1.getId() );

		cte1.getComps().add( new Component1( "a", "b" ) );

		em.getTransaction().commit();

		id1 = cte1.getId();
		id2 = cte2.getId();
	}
