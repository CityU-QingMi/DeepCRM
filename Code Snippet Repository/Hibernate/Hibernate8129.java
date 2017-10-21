	@Test
	public void testMapKeyExpressionInWhere() {
		// NOTE : JPA requires that an alias be used in the key() expression.  Hibernate allows
		//		path or alias.

		Session s = openSession();
		s.getTransaction().begin();

		// JPA form
		List contacts = s.createQuery( "select c from Contact c join c.addresses a where key(a) is not null" ).list();
		assertEquals( 1, contacts.size() );
		Contact contact = assertTyping( Contact.class, contacts.get( 0 ) );

		// Hibernate additional form
		contacts = s.createQuery( "select c from Contact c where key(c.addresses) is not null" ).list();
		assertEquals( 1, contacts.size() );
		contact = assertTyping( Contact.class, contacts.get( 0 ) );

		s.getTransaction().commit();
		s.close();
	}
