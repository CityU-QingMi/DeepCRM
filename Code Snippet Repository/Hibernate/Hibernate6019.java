	@Test
	public void testBindingCalendarAsDate() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "findMessagesByDate" );
			query.registerStoredProcedureParameter( 1, Calendar.class, ParameterMode.IN );
			query.setParameter( 1, nowCal, TemporalType.DATE );
			List list = query.getResultList();
			assertEquals( 1, list.size() );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
