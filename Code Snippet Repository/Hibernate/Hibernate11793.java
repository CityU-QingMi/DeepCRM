	public List listEventsWithCriteria() {
		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		List result = session.createCriteria(Event.class)
			.setCacheable(true)
			.list();

		session.getTransaction().commit();

		return result;
	}
