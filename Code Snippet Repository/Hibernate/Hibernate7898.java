	@Test
	public void testRowValueConstructorSyntaxInInList() {
		Session s = openSession();
		s.beginTransaction();
		Product product = new Product();
		product.setDescription( "My Product" );
		product.setNumberAvailable( 10 );
		product.setPrice( new BigDecimal( 123 ) );
		product.setProductId( "4321" );
		s.save( product );


		Customer customer = new Customer();
		customer.setCustomerId( "123456789" );
		customer.setName( "My customer" );
		customer.setAddress( "somewhere" );
		s.save( customer );

		Order order = customer.generateNewOrder( new BigDecimal( 1234 ) );
		s.save( order );

		LineItem li = order.generateLineItem( product, 5 );
		s.save( li );
		product = new Product();
		product.setDescription( "My Product" );
		product.setNumberAvailable( 10 );
		product.setPrice( new BigDecimal( 123 ) );
		product.setProductId( "1234" );
		s.save( product );
		li = order.generateLineItem( product, 10 );
		s.save( li );

		s.flush();
		Query query = s.createQuery( "from LineItem l where l.id in (:idList)" );
		List<Id> list = new ArrayList<Id>();
		list.add( new Id( "123456789", order.getId().getOrderNumber(), "4321" ) );
		list.add( new Id( "123456789", order.getId().getOrderNumber(), "1234" ) );
		query.setParameterList( "idList", list );
		assertEquals( 2, query.list().size() );

		query = s.createQuery( "from LineItem l where l.id in :idList" );
		query.setParameterList( "idList", list );
		assertEquals( 2, query.list().size() );

		s.getTransaction().rollback();
		s.close();

	}
