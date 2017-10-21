	@Test
	public void testIt() {
		{
			Session s = openSession();
			s.beginTransaction();
			LocalOrder order = s.byId( LocalOrder.class ).load( 1 );
			assertEquals( 2, order.lineItems.size() );
			LocalLineItem shoes = order.lineItems.get( 0 );
			LocalLineItem socks = order.lineItems.get( 1 );
			assertEquals( "Shoes", shoes.name );
			assertEquals( 0, shoes.position );
			assertEquals( 1, socks.position );
			order.lineItems.remove( socks );
			order.lineItems.add( 0, socks );
			s.getTransaction().commit();
			s.close();
		}

		{
			Session s = openSession();
			s.beginTransaction();
			LocalOrder order = s.byId( LocalOrder.class ).load( 1 );
			assertEquals( 2, order.lineItems.size() );
			LocalLineItem socks = order.lineItems.get( 0 );
			LocalLineItem shoes = order.lineItems.get( 1 );
			assertEquals( "Shoes", shoes.name );
			assertEquals( 0, socks.position );
			assertEquals( 1, shoes.position );

			s.getTransaction().commit();
			s.close();
		}
	}
