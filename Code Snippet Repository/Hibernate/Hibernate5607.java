	private PersonBase createPerson(PersonBase person, String address) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		person.addresses.add( new Address( address ) );
		person = em.merge( person );
		tx.commit();
		return person;
	}
