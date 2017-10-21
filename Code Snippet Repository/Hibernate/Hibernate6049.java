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
			query.setParameter( 1, 1 );

			assertEquals( "aName", query.getOutputParameterValue( 2 ) );
		}
		finally {
			em.close();
		}
	}
