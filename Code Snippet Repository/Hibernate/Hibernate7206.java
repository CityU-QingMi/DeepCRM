	private People createPeople() {
		Session session = openSession();
		session.beginTransaction();
		People people = new People();
		people.people.add( new Person() );
		people.people.add( new Person() );
		session.persist( people );

		session.getTransaction().commit();
		session.close();
		return people;
	}
