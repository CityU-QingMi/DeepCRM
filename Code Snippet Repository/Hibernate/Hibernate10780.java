	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		ComponentMapKeyEntity imke = new ComponentMapKeyEntity();

		// Revision 1 (intialy 1 mapping)
		em.getTransaction().begin();

		ComponentTestEntity cte1 = new ComponentTestEntity(
				new Component1( "x1", "y2" ), new Component2(
				"a1",
				"b2"
		)
		);
		ComponentTestEntity cte2 = new ComponentTestEntity(
				new Component1( "x1", "y2" ), new Component2(
				"a1",
				"b2"
		)
		);

		em.persist( cte1 );
		em.persist( cte2 );

		imke.getIdmap().put( cte1.getComp1(), cte1 );

		em.persist( imke );

		em.getTransaction().commit();

		// Revision 2 (sse1: adding 1 mapping)
		em.getTransaction().begin();

		cte2 = em.find( ComponentTestEntity.class, cte2.getId() );
		imke = em.find( ComponentMapKeyEntity.class, imke.getId() );

		imke.getIdmap().put( cte2.getComp1(), cte2 );

		em.getTransaction().commit();

		//

		cmke_id = imke.getId();

		cte1_id = cte1.getId();
		cte2_id = cte2.getId();
	}
