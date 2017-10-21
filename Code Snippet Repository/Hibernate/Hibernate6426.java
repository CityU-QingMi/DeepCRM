	@Test
	@TestForIssue(jiraKey = "")
	public void testMergeTransientIdManyToOne() throws Exception {
		ShoppingCart transientCart = new ShoppingCart( "cart1" );
		transientCart.addLineItem( new LineItem( 0, "description2", transientCart ) );

		// assertion for HHH-11274 - checking for exception
		final Object identifier = new PersistenceUnitUtilImpl( sessionFactory() ).getIdentifier( transientCart.getLineItems().get( 0 ) );

		// merge ID with transient many-to-one
		Session s = openSession();
		s.getTransaction().begin();
		s.merge( transientCart );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		ShoppingCart updatedCart = s.get( ShoppingCart.class, "cart1" );
		// assertion for HHH-11274 - checking for exception
		new PersistenceUnitUtilImpl( sessionFactory() ).getIdentifier( transientCart.getLineItems().get( 0 ) );
		assertEquals( 1, updatedCart.getLineItems().size() );
		assertEquals( "description2", updatedCart.getLineItems().get( 0 ).getDescription() );
		s.getTransaction().commit();
		s.close();
	}
