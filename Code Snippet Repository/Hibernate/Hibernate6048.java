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
			query.setParameter( "ID_PARAM", 1 );

			assertEquals( "aName", query.getOutputParameterValue( "NAME_PARAM" ) );
			assertEquals( 29, query.getOutputParameterValue( "AGE_PARAM" ) );
		}
		finally {
			em.close();
		}
	}
