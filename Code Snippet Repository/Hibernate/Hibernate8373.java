	@Test
	public void testMergeNonNullOptionalJoinToDiffNonNullDetached() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		// create a new thing with a non-null name
		Thing thing = new Thing();
		thing.setName("one");
		s.save(thing);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List things = s.createQuery("from Thing").list();
		assertEquals(1, things.size());
		thing = (Thing)things.get(0);
		assertEquals("one", thing.getName());
		assertEquals("ONE", thing.getNameUpper());
		t.commit();
		s.close();

		// change detached thing name to a new non-null name and save it
		thing.setName("one_changed");

		s = openSession();
		t = s.beginTransaction();
		s.merge(thing);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		things = s.createQuery("from Thing").list();
		assertEquals(1, things.size());
		thing = (Thing)things.get(0);
		assertEquals("one_changed", thing.getName());
		assertEquals("ONE_CHANGED", thing.getNameUpper());
		s.delete(thing);
		t.commit();
		s.close();
	}
