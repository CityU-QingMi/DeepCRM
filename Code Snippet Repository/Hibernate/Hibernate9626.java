	@Test
	public void testUnionSubclassCollection() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Location mel = new Location("Earth");
		s.save(mel);
		
		Human gavin = new Human();
		gavin.setIdentity("gavin");
		gavin.setSex('M');
		gavin.setLocation(mel);
		mel.addBeing(gavin);
		
		gavin.getInfo().put("foo", "bar");
		gavin.getInfo().put("x", "y");
		
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		gavin = (Human) s.createCriteria(Human.class).uniqueResult();
		assertEquals( gavin.getInfo().size(), 2 );
		s.delete(gavin);
		s.delete( gavin.getLocation() );
		t.commit();
		s.close();
	}
