	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1 - add
		em.getTransaction().begin();
		ObjectUserTypeEntity entity = new ObjectUserTypeEntity( "builtInType1", "stringUserType1" );
		em.persist( entity );
		em.getTransaction().commit();

		id = entity.getId();

		// Revision 2 - modify
		em.getTransaction().begin();
		entity = em.find( ObjectUserTypeEntity.class, entity.getId() );
		entity.setUserType( 2 );
		entity = em.merge( entity );
		em.getTransaction().commit();

		// Revision 3 - remove
		em.getTransaction().begin();
		entity = em.find( ObjectUserTypeEntity.class, entity.getId() );
		em.remove( entity );
		em.getTransaction().commit();

		em.close();
	}
