	public Long createAndStorePerson(Person person) {

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		Long personId = (Long)session.save(person);

		session.getTransaction().commit();
		return personId;
	}
