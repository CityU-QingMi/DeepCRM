	@Test
	public void testMergeNonNullOptionalJoinToNullDetached() throws Exception {
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

		// give detached thing a null name and save it
		thing.setName(null);

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
		assertNull(thing.getName());
		assertNull(thing.getNameUpper());
		s.delete(thing);
		t.commit();
		s.close();
	}
