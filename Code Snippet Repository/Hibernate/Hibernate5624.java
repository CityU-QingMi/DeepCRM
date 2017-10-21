	@Test
	public void testDateTimeFunctions() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilderImpl cb = (CriteriaBuilderImpl) em.getCriteriaBuilder();
		MetamodelImpl mm = (MetamodelImpl) em.getMetamodel();

        CriteriaQuery<java.sql.Date> dateQuery = cb.createQuery(java.sql.Date.class);
		dateQuery.from( Customer.class );
		dateQuery.select( cb.currentDate() );
		em.createQuery( dateQuery ).getResultList();

        CriteriaQuery<java.sql.Time> timeQuery = cb.createQuery(java.sql.Time.class);
		timeQuery.from( Customer.class );
		timeQuery.select( cb.currentTime() );
		em.createQuery( timeQuery ).getResultList();

        CriteriaQuery<java.sql.Timestamp> tsQuery = cb.createQuery(java.sql.Timestamp.class);
		tsQuery.from( Customer.class );
		tsQuery.select( cb.currentTimestamp() );
		em.createQuery( tsQuery ).getResultList();

		em.getTransaction().commit();
		em.close();
	}
