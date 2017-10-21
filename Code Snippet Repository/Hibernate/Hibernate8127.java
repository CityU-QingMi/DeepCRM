	@Before
	public void prepareTestData() {
		Session s = openSession();
		s.getTransaction().begin();

		AddressType homeType = new AddressType( 1, "home" );
		s.persist( homeType );

		Address address = new Address( 1, "Main St.", "Somewhere, USA" );
		s.persist( address );

		Contact contact = new Contact( 1, "John" );
		contact.addresses.put( homeType, address );
		s.persist( contact );

		s.getTransaction().commit();
		s.close();
	}
