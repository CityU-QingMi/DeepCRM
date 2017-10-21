	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		FieldAccessTypeEntity fate = new FieldAccessTypeEntity( "data" );
		em.persist( fate );
		id1 = fate.readId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		fate = em.find( FieldAccessTypeEntity.class, id1 );
		fate.writeData( "data2" );
		em.getTransaction().commit();
	}
