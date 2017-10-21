	protected void prepareTest() {
		Session s = openSession();
		s.getTransaction().begin();

		// Order with one mathing line
		order1 = new Order();
		OrderLine line = new OrderLine();
		line.setArticleId( "1000" );
		order1.addLine( line );
		line = new OrderLine();
		line.setArticleId( "3000" );
		order1.addLine( line );
		s.persist( order1 );

		OrderContact contact = new OrderContact();
		contact.setContact( "Contact1" );
		order1.addContact( contact );
		contact = new OrderContact();
		contact.setContact( "Contact2" );
		order1.addContact( contact );

		OrderAddress orderAddress = new OrderAddress();
		Address address = new Address();
		address.setAddressText( "over the rainbow" );
		orderAddress.setDeliveryAddress( address );
		Address otherAddress = new Address();
		otherAddress.setAddressText( "other place" );
		orderAddress.getNotifiedAddresses().add( address );
		orderAddress.getNotifiedAddresses().add( otherAddress );
		order1.setOrderAddress( orderAddress );

		s.persist( order1 );

		// Order with no lines
		order2 = new Order();
		contact = new OrderContact();
		contact.setContact( "Contact1" );
		order2.addContact( contact );
		s.persist( order2 );

		// Order with non-matching line
		order3 = new Order();
		line = new OrderLine();
		line.setArticleId( "3000" );
		order3.addLine( line );
		s.persist( order3 );

		s.getTransaction().commit();
		s.close();
	}
