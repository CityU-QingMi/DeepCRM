	@Test
	public void testCreateQueryNull() {
		Customer cust = new Customer();
		cust.setCustomerId("xyz123");
		cust.setName("Matt");
		
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist(cust);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		List results = s.createQuery("from Customer cust left join fetch cust.billingAddress where cust.customerId='xyz123'").list();
		//List results = s.createQuery("from Customer cust left join fetch cust.billingAddress left join fetch cust.shippingAddress").list();
		cust = (Customer) results.get(0);
		assertNull( cust.getShippingAddress() );
		assertNull( cust.getBillingAddress() );
		s.delete(cust);
		t.commit();
		s.close();
		
	}
