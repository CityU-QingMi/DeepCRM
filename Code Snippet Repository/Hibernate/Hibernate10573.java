	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		StrTestEntity entity = new StrTestEntity( "x" );
		em.persist( entity );
		em.getTransaction().commit();

		id = entity.getId();

		// Revision 2
		em.getTransaction().begin();
		entity = em.find( StrTestEntity.class, entity.getId() );
		entity.setStr( "y" );
		entity = em.merge( entity );
		em.getTransaction().commit();

		em.close();
	}
