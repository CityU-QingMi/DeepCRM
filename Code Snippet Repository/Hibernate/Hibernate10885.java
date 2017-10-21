	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		NotInsertableTestEntity dte = new NotInsertableTestEntity( "data1" );
		em.persist( dte );
		id1 = dte.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		dte = em.find( NotInsertableTestEntity.class, id1 );
		dte.setData( "data2" );
		em.getTransaction().commit();
	}
