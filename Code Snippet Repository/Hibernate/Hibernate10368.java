	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		MixedAccessTypeEntity mate = new MixedAccessTypeEntity( "data" );
		em.persist( mate );
		id1 = mate.readId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		mate = em.find( MixedAccessTypeEntity.class, id1 );
		mate.writeData( "data2" );
		em.getTransaction().commit();
	}
