	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		BasicTestEntity1 entity = new BasicTestEntity1( "str1", 1 );
		em.persist( entity );
		em.getTransaction().commit();

		id = entity.getId();

		// Revision 2 - both properties (str1 and long1) should be marked as modified.
		em.getTransaction().begin();
		entity = em.find( BasicTestEntity1.class, entity.getId() );
		entity.setStr1( "str2" );
		entity = em.merge( entity );
		em.flush();
		entity.setLong1( 2 );
		entity = em.merge( entity );
		em.flush();
		em.getTransaction().commit();

		em.close();
	}
