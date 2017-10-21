	public List listEventsOfOrganizer(Person organizer) {

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		Query query = session.createQuery("from Event ev where ev.organizer = :organizer");

		query.setCacheable(true);
		query.setEntity("organizer", organizer);
		List result = query.list();

		session.getTransaction().commit();

		return result;
	}
