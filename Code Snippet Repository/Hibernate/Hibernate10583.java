	@Test
	@Priority(10)
	public void initData() {
		EntityManager entityManager = getOrCreateEntityManager();
		try {
			// Revision 1
			SimpleEntity se = new SimpleEntity();
			se.setData( "data" );
			entityManager.getTransaction().begin();
			entityManager.persist( se );
			entityManager.getTransaction().commit();
			entityManager.clear();
			entityId = se.getId();

			// Revision 2
			entityManager.getTransaction().begin();
			se = entityManager.find( SimpleEntity.class, se.getId() );
			se.setData( "data2" );
			entityManager.merge( se );
			entityManager.getTransaction().commit();

			// Revision 3
			entityManager.getTransaction().begin();
			se = entityManager.find( SimpleEntity.class, se.getId() );
			entityManager.remove( se );
			entityManager.getTransaction().commit();

		}
		finally {
			entityManager.close();
		}
	}
