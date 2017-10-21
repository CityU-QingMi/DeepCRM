	public Long createAndStoreEvent(String title, Person organizer, Date theDate) {

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		theEvent.setOrganizer(organizer);

		Long eventId = (Long)session.save(theEvent);

		session.getTransaction().commit();
		return eventId;
	}
