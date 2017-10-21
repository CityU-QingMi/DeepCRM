	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		PropertyNotUpdatableEntity entity = new PropertyNotUpdatableEntity(
				"data",
				"constant data 1",
				"constant data 2"
		);
		em.persist( entity );
		em.getTransaction().commit();

		id = entity.getId();

		// Revision 2
		em.getTransaction().begin();
		entity = em.find( PropertyNotUpdatableEntity.class, entity.getId() );
		entity.setData( "modified data" );
		entity.setConstantData1( null );
		em.merge( entity );
		em.getTransaction().commit();

		em.close();
		em = getEntityManager(); // Re-opening entity manager to re-initialize non-updatable fields
		// with database values. Otherwise PostUpdateEvent#getOldState() returns previous
		// memory state. This can be achieved using EntityManager#refresh(Object) method as well.

		// Revision 3
		em.getTransaction().begin();
		entity = em.find( PropertyNotUpdatableEntity.class, entity.getId() );
		entity.setData( "another modified data" );
		entity.setConstantData2( "invalid data" );
		em.merge( entity );
		em.getTransaction().commit();

		// Revision 4
		em.getTransaction().begin();
		em.refresh( entity );
		em.remove( entity );
		em.getTransaction().commit();
	}
