	public void addPersonToEvent(Long personId, Long eventId) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person)session.load(Person.class, personId);
		Event anEvent = (Event)session.load(Event.class, eventId);

		aPerson.getEvents().add(anEvent);

		session.getTransaction().commit();
	}
