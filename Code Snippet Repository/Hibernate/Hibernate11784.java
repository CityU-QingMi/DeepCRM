	public void addPhoneNumberToPerson(Long personId, PhoneNumber pN) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person)session.load(Person.class, personId);
		pN.setPersonId(personId.longValue());
		aPerson.getPhoneNumbers().add(pN);

		session.getTransaction().commit();
	}
