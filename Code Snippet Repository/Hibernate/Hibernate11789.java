	public Long createAndStorePerson(String firstName, String lastName) {

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		Person person = new Person();
		person.setFirstname(firstName);
		person.setLastname(lastName);

		Long personId = (Long)session.save(person);

		session.getTransaction().commit();
		return personId;
	}
