	@Test
	public void testLockModeTypeRead() {
		if ( !readCommittedIsolationMaintained( "ejb3 lock tests" ) ) {
			return;
		}
		final String initialName = "lock test";
		// set up some test data
		Session s1 = sessionFactory().openSession();
		Transaction t1 = s1.beginTransaction();
		Item item = new Item();
		item.setName( initialName );
		s1.save( item );
		t1.commit();
		s1.close();

		Long itemId = item.getId();

		// do the isolated update
		s1 = sessionFactory().openSession();
		t1 = s1.beginTransaction();
		item = (Item) s1.get( Item.class, itemId );
		s1.lock( item, LockMode.UPGRADE );
		item.setName( "updated" );
		s1.flush();

		Session s2 = sessionFactory().openSession();
		Transaction t2 = s2.beginTransaction();
		Item item2 = (Item) s2.get( Item.class, itemId );
		assertEquals( "isolation not maintained", initialName, item2.getName() );

		t1.commit();
		s1.close();

		item2 = (Item) s2.get( Item.class, itemId );
		assertEquals( "repeatable read not maintained", initialName, item2.getName() );
		t2.commit();
		s2.close();

		s1 = sessionFactory().openSession();
		t1 = s1.beginTransaction();
		s1.delete( item );
		t1.commit();
		s1.close();
	}
