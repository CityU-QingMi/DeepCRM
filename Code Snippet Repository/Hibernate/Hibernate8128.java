	@After
	public void cleanUpTestData() {
		Session s = openSession();
		s.getTransaction().begin();

		s.delete( s.get( Contact.class, 1 ) );

		s.delete( s.get( Address.class, 1 ) );
		s.delete( s.get( AddressType.class, 1 ) );

		s.getTransaction().commit();
		s.close();
	}
