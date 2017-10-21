	public void addEmailToPerson(Long personId, String emailAddress) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person)session.load(Person.class, personId);

		// The getEmailAddresses() might trigger a lazy load of the collection
		aPerson.getEmailAddresses().add(emailAddress);

		session.getTransaction().commit();
	}
