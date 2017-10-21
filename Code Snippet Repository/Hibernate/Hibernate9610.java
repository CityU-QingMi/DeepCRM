	@Test
	public void testCreateQuery() {
		Customer cust = new Customer();
		cust.setCustomerId("abc123");
		cust.setName("Matt");
		
		Address ship = new Address();
		ship.setStreet("peachtree rd");
		ship.setState("GA");
		ship.setCity("ATL");
		ship.setZip("30326");
		ship.setAddressId( new AddressId("SHIPPING", "abc123") );
		ship.setCustomer(cust);
		
		Address bill = new Address();
		bill.setStreet("peachtree rd");
		bill.setState("GA");
		bill.setCity("ATL");
		bill.setZip("30326");
		bill.setAddressId( new AddressId("BILLING", "abc123") );
		bill.setCustomer(cust);
		
		cust.setBillingAddress(bill);
		cust.setShippingAddress(ship);
		
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist(cust);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		List results = s.createQuery("from Customer cust left join fetch cust.billingAddress where cust.customerId='abc123'").list();
		//List results = s.createQuery("from Customer cust left join fetch cust.billingAddress left join fetch cust.shippingAddress").list();
		cust = (Customer) results.get(0);
		assertTrue( Hibernate.isInitialized( cust.getShippingAddress() ) );
		assertTrue( Hibernate.isInitialized( cust.getBillingAddress() ) );
		assertEquals( "30326", cust.getBillingAddress().getZip() );
		assertEquals( "30326", cust.getShippingAddress().getZip() );
		assertEquals( "BILLING", cust.getBillingAddress().getAddressId().getType() );
		assertEquals( "SHIPPING", cust.getShippingAddress().getAddressId().getType() );
		s.delete( cust );
		t.commit();
		s.close();
	}
