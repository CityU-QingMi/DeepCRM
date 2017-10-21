	@Test
	public void testStaleVersionedInstanceFoundInQueryResult() {
		String check = "EJB3 Specification";
		Session s1 = sessionFactory().openSession();
		Transaction t1 = s1.beginTransaction();
		Item item = new Item( check );
		s1.save(  item );
		t1.commit();
		s1.close();

		Long itemId = item.getId();
		long initialVersion = item.getVersion();

		// Now, open a new Session and re-load the item...
		s1 = sessionFactory().openSession();
		t1 = s1.beginTransaction();
		item = ( Item ) s1.get( Item.class, itemId );

		// now that the item is associated with the persistence-context of that session,
		// open a new session and modify it "behind the back" of the first session
		Session s2 = sessionFactory().openSession();
		Transaction t2 = s2.beginTransaction();
		Item item2 = ( Item ) s2.get( Item.class, itemId );
		item2.setName( "EJB3 Persistence Spec" );
		t2.commit();
		s2.close();

		// at this point, s1 now contains stale data, so try an hql query which
		// returns said item and make sure we get the previously associated state
		// (i.e., the old name and the old version)
		item2 = ( Item ) s1.createQuery( "select i from Item i" ).list().get( 0 );
		assertTrue( item == item2 );
		assertEquals( "encountered non-repeatable read", check, item2.getName() );
		assertEquals( "encountered non-repeatable read", initialVersion, item2.getVersion() );

		t1.commit();
		s1.close();

		// clean up
		s1 = sessionFactory().openSession();
		t1 = s1.beginTransaction();
		s1.createQuery( "delete Item" ).executeUpdate();
		t1.commit();
		s1.close();
	}
