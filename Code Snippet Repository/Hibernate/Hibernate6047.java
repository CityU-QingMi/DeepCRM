	@Test
	public void testOneBasicOutParameter() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User user = new User();
		user.id = 1;
		user.name = "aName";
		em.persist( user );
		em.getTransaction().commit();

		em.clear();

		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.findNameById" );
			query.setParameter( "ID_PARAM", 1 );

			assertEquals( "aName", query.getOutputParameterValue( "NAME_PARAM" ) );
		}
		finally {
			em.close();
		}
	}
