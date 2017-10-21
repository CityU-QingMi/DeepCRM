	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1 - Adding two entities
		em.getTransaction().begin();
		StrTestEntity ste1 = new StrTestEntity( "x" );
		StrTestEntity ste2 = new StrTestEntity( "y" );
		em.persist( ste1 );
		em.persist( ste2 );
		steId1 = ste1.getId();
		steId2 = ste2.getId();
		em.getTransaction().commit();

		// Revision 2 - Adding first and removing second entity
		em.getTransaction().begin();
		ste1 = em.find( StrTestEntity.class, steId1 );
		ste2 = em.find( StrTestEntity.class, steId2 );
		ste1.setStr( "z" );
		em.remove( ste2 );
		em.getTransaction().commit();

		// Revision 3 - Modifying and removing the same entity.
		em.getTransaction().begin();
		ste1 = em.find( StrTestEntity.class, steId1 );
		ste1.setStr( "a" );
		em.merge( ste1 );
		em.remove( ste1 );
		em.getTransaction().commit();
	}
