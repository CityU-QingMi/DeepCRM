	@Test
	public void testPassNull() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User user1 = new User();
		user1.id = 1;
		user1.name = "aName";
		user1.age = 99;
		em.persist( user1 );
		em.getTransaction().commit();

		em.clear();

		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.findNameById" );
			query.setParameter( "ID_PARAM", 1 );
			// null is passed for EXTRA_IN_PARAM

			assertEquals( "aName", query.getOutputParameterValue( "NAME_PARAM" ) );
			assertEquals( null, query.getOutputParameterValue( "EXTRA_OUT_PARAM" ) );
		}
		finally {
			em.close();
		}
	}
