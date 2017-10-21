	@Test
	public void testTwoBasicOutParameters() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User user = new User();
		user.id = 1;
		user.name = "aName";
		user.age = 29;
		em.persist( user );
		em.getTransaction().commit();

		em.clear();

		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.findNameAndAgeById" );
			query.setParameter( 1, 1 );

			assertEquals( "aName", query.getOutputParameterValue( 2 ) );
			assertEquals( 29, query.getOutputParameterValue( 3 ) );
		}
		finally {
			em.close();
		}
	}
