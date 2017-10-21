	private PersonBaseBase createPerson(PersonBaseBase person, String address, String localAddress) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		person.addresses.add( new Address( address ) );
		person.getLocalAddresses().add( new Address( localAddress ) );
		person = em.merge( person );
		tx.commit();
		return person;
	}
