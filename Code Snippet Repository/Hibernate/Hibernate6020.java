	@Test
	public void testBindingCalendarAsTime() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "findMessagesByTime" );
			query.registerStoredProcedureParameter( 1, Calendar.class, ParameterMode.IN );
			query.setParameter( 1, nowCal, TemporalType.TIME );
			List list = query.getResultList();
			assertEquals( 1, list.size() );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
