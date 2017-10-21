	@Test
	public void testStoredProcedureRefCursor() {

		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "sp_phones" );
			query.registerStoredProcedureParameter( 1, void.class, ParameterMode.REF_CURSOR );
			query.registerStoredProcedureParameter( 2, Long.class, ParameterMode.IN );

			query.setParameter( 2, 1L );

			List<Object[]> personComments = query.getResultList();
			assertEquals( 2, personComments.size() );
		}
		catch (Exception e) {
			assertTrue( Pattern.compile( "Dialect .*? not known to support REF_CURSOR parameters" )
								.matcher( e.getCause().getMessage() )
								.matches() );
		}
		finally {
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}
