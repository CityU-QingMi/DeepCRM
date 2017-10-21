	@Test
	public void testMapValueExpressionInSelect() {
		Session s = openSession();
		s.getTransaction().begin();

		List addresses = s.createQuery( "select value(a) from Contact c join c.addresses a" ).list();
		assertEquals( 1, addresses.size() );
		assertTyping( Address.class, addresses.get( 0 ) );

		addresses = s.createQuery( "select value(c.addresses) from Contact c" ).list();
		assertEquals( 1, addresses.size() );
		assertTyping( Address.class, addresses.get( 0 ) );

		s.getTransaction().commit();
		s.close();
	}
