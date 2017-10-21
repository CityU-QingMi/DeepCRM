	@Test
	public void testBindingNulls() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		try {
			Query query = em.createQuery( "from Thing t where t.someDate = :aDate or t.someTime = :aTime or t.someTimestamp = :aTimestamp" );
			query.setParameter( "aDate", (Date) null, TemporalType.DATE );
			query.setParameter( "aTime", (Date) null, TemporalType.DATE );
			query.setParameter( "aTimestamp", (Date) null, TemporalType.DATE );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
