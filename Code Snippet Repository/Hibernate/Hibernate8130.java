	@Test
	public void testMapKeyExpressionInSelect() {
		// NOTE : JPA requires that an alias be used in the key() expression.  Hibernate allows
		//		path or alias.

		Session s = openSession();
		s.getTransaction().begin();

		// JPA form
		List types = s.createQuery( "select key(a) from Contact c join c.addresses a" ).list();
		assertEquals( 1, types.size() );
		assertTyping( AddressType.class, types.get( 0 ) );

		// Hibernate additional form
		types = s.createQuery( "select key(c.addresses) from Contact c" ).list();
		assertEquals( 1, types.size() );
		assertTyping( AddressType.class, types.get( 0 ) );

		s.getTransaction().commit();
		s.close();
	}
