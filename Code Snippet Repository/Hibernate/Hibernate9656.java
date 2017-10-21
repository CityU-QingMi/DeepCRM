	@Test
	public void testVersionShortCircuitFlush() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person gavin = new Person("Gavin");
		new Thing("Passport", gavin);
		s.persist(gavin);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		Thing passp = (Thing) s.get(Thing.class, "Passport");
		passp.setLongDescription("blah blah blah");
		s.createQuery("from Person").list();
		s.createQuery("from Person").list();
		s.createQuery("from Person").list();
		t.commit();
		s.close();
		
		assertEquals( passp.getVersion(), 1 );

		s = openSession();
		t = s.beginTransaction();
		s.createQuery("delete from Thing").executeUpdate();
		s.createQuery("delete from Person").executeUpdate();
		t.commit();
		s.close();
	}
