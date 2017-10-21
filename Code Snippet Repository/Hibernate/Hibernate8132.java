	@Test
	public void testMapEntryExpressionInSelect() {
		Session s = openSession();
		s.getTransaction().begin();

		List addresses = s.createQuery( "select entry(a) from Contact c join c.addresses a" ).list();
		assertEquals( 1, addresses.size() );
		assertTyping( Map.Entry.class, addresses.get( 0 ) );

		addresses = s.createQuery( "select entry(c.addresses) from Contact c" ).list();
		assertEquals( 1, addresses.size() );
		assertTyping( Map.Entry.class, addresses.get( 0 ) );

		s.getTransaction().commit();
		s.close();
	}
