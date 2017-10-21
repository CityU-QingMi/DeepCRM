	@Test
	public void testPropertyRefToJoinedSubclass() {
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		Person p = new Person();
		p.setName("Gavin King");
		BankAccount acc = new BankAccount();
		acc.setBsb("0634");
		acc.setType('B');
		acc.setAccountNumber("xxx-123-abc");
		p.setBankAccount(acc);
		session.persist(p);
		tx.commit();
		session.close();

		session = openSession();
		tx = session.beginTransaction();
		p = (Person) session.get(Person.class, p.getId());
		assertNotNull( p.getBankAccount() );
		assertTrue( Hibernate.isInitialized( p.getBankAccount() ) );
		tx.commit();
		session.close();

		session = openSession();
		tx = session.beginTransaction();
		p = (Person) session.createCriteria(Person.class)
			.setFetchMode("bankAccount", FetchMode.JOIN)
			.uniqueResult();
		assertNotNull( p.getBankAccount() );
		assertTrue( Hibernate.isInitialized( p.getBankAccount() ) );
		tx.commit();
		session.close();

		session = openSession();
		tx = session.beginTransaction();
		session.delete(p);
		tx.commit();
		session.close();
	}
