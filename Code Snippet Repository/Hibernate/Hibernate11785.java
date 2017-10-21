	public void addTalismanToPerson(Long personId, String talisman) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person)session.load(Person.class, personId);
		aPerson.addTalisman(talisman);

		session.getTransaction().commit();
	}
