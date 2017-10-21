	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		StrTestEntity ste1 = new StrTestEntity();
		ste1.setStr( "str1" );

		StrTestEntity ste2 = new StrTestEntity();
		ste2.setStr( "str2" );

		em.persist( ste1 );
		em.persist( ste2 );

		em.getTransaction().commit();

		// Revision 2
		em = getEntityManager();
		em.getTransaction().begin();

		OneToManyComponentTestEntity otmcte1 = new OneToManyComponentTestEntity( new OneToManyComponent( "data1" ) );
		otmcte1.getComp1().getEntities().add( ste1 );

		em.persist( otmcte1 );

		em.getTransaction().commit();

		// Revision 3
		em = getEntityManager();
		em.getTransaction().begin();

		otmcte1 = em.find( OneToManyComponentTestEntity.class, otmcte1.getId() );
		otmcte1.getComp1().getEntities().add( ste2 );

		em.getTransaction().commit();

		otmcte_id1 = otmcte1.getId();
		ste_id1 = ste1.getId();
		ste_id2 = ste2.getId();
	}
