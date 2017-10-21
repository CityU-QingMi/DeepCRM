	public List listEmailsOfEvent(Long eventId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		List emailList = new ArrayList();
		Event event = (Event)session.load(Event.class, eventId);
		for (Iterator it = event.getParticipants().iterator(); it.hasNext(); ) {
			Person person = (Person)it.next();
			emailList.addAll(person.getEmailAddresses());
		}

		session.getTransaction().commit();
		return emailList;
	}
