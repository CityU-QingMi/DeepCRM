	private void doTest(String property, Object obj, TemporalType temporalType) {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from DataPoint where " + property + " = :obj");
		if (obj instanceof Calendar) {
			query.setParameter("obj", (Calendar) obj, temporalType);
		}
		else {
			query.setParameter("obj", (Date) obj, temporalType);
		}
		
		em.getTransaction().commit();
		em.close();
	}
