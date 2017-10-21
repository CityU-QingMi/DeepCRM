	public Long addPersonToAccount(Long personId, Account account) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Person aPerson = (Person)session.load(Person.class, personId);
		account.setPerson(aPerson);

		Long accountId = (Long)session.save(account);

		session.getTransaction().commit();
		return accountId;
	}
