	@Test
	public void testBindingCalendarAsDate() {
		createTestData();

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		try {
			Query query = em.createQuery( "from Thing t where t.someDate = :aDate" );
			query.setParameter( "aDate", nowCal, TemporalType.DATE );
			List list = query.getResultList();
			assertEquals( 1, list.size() );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}

		deleteTestData();
	}
