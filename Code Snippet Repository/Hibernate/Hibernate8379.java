	@Test
	public void testMergeNullOptionalJoinToNonNullDetached() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		// create a new thing with a null name
		Thing thing = new Thing();
		thing.setName(null);
		s.save(thing);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List things = s.createQuery("from Thing").list();
		assertEquals(1, things.size());
		thing = (Thing)things.get(0);
		assertNull(thing.getName());
		assertNull(thing.getNameUpper());
		t.commit();
		s.close();

		// change detached thing name to a non-null value
		thing.setName("two");

		s = openSession();
		t = s.beginTransaction();
		s.merge(thing);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		things = s.createQuery("from Thing").list();
		assertEquals(1, things.size());
		thing = ((Thing) things.get(0));
		assertEquals("two", thing.getName());
		assertEquals("TWO", thing.getNameUpper());
		s.delete(thing);
		t.commit();
		s.close();
	}
