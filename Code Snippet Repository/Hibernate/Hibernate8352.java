	@Test
	public void testIterate() throws Exception {
		sessionFactory().getStatistics().clear();
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Item i1 = new Item("foo");
		Item i2 = new Item("bar");
		s.persist("Item", i1);
		s.persist("Item", i2);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Iterator iter = s.getNamedQuery("Item.nameDesc").iterate();
		i1 = (Item) iter.next();
		i2 = (Item) iter.next();
		assertFalse( Hibernate.isInitialized(i1) );
		assertFalse( Hibernate.isInitialized(i2) );
		i1.getName();
		i2.getName();
		assertFalse( Hibernate.isInitialized(i1) );
		assertFalse( Hibernate.isInitialized(i2) );
		assertEquals( i1.getName(), "foo" );
		assertEquals( i2.getName(), "bar" );
		Hibernate.initialize(i1);
		assertFalse( iter.hasNext() );
		s.delete(i1);
		s.delete(i2);
		t.commit();
		s.close();
		assertEquals( sessionFactory().getStatistics().getEntityFetchCount(), 2 );
	}
