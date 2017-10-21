	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1 - Adding two entities
		em.getTransaction().begin();
		StrTestEntity ste = new StrTestEntity( "x" );
		StrIntTestEntity site = new StrIntTestEntity( "y", 1 );
		em.persist( ste );
		em.persist( site );
		steId = ste.getId();
		siteId = site.getId();
		em.getTransaction().commit();

		// Revision 2 - Modifying one entity
		em.getTransaction().begin();
		site = em.find( StrIntTestEntity.class, siteId );
		site.setNumber( 2 );
		em.getTransaction().commit();

		// Revision 3 - Deleting both entities
		em.getTransaction().begin();
		ste = em.find( StrTestEntity.class, steId );
		site = em.find( StrIntTestEntity.class, siteId );
		em.remove( ste );
		em.remove( site );
		em.getTransaction().commit();
	}
