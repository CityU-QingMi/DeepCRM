	@Test
	@Priority(10)
	public void initData() {
		EntityManager entityManager = getEntityManager();
		try {
			// Revision 1
			entityManager.getTransaction().begin();
			BasicTestEntity1 entity = new BasicTestEntity1( "str1", 1 );
			entityManager.persist( entity );
			entity.setStr1( "str2" );
			entityManager.merge( entity );
			entityManager.getTransaction().commit();
		}
		finally {
			entityManager.close();
		}
	}
