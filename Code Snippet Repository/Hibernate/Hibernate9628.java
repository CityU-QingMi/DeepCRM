	@Test
	public void testNestedUnionedSubclasses() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Location mel = new Location("Earth");
		Human marcf = new Human();
		marcf.setIdentity("marc");
		marcf.setSex('M');
		mel.addBeing(marcf);
		Employee steve = new Employee();
		steve.setIdentity("steve");
		steve.setSex('M');
		steve.setSalary( (double) 0 );
		mel.addBeing(steve);
		s.persist(mel);
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		Query q = s.createQuery( "from Being h where h.identity = :name1 or h.identity = :name2" );
		q.setString("name1", "marc");
		q.setString("name2", "steve");
		final List result = q.list();
		assertEquals( 2, result.size() );
		s.delete( result.get(0) );
		s.delete( result.get(1) );
		s.delete( ( (Human) result.get(0) ).getLocation() );
		tx.commit();
		s.close();
	}
