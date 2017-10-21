	@Test
	public void testSetPropertiesMap() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Simple simple = new Simple( Long.valueOf(10) );
		simple.setName("Simple 1");
		s.save( simple );

		Map<String,Object> parameters = new HashMap<>();
		parameters.put( "name", simple.getName() );
		parameters.put( "count", simple.getCount() );
		Query q = s.createQuery("from Simple s where s.name=:name and s.count=:count");
		q.setProperties((parameters));
		assertTrue( q.list().get(0)==simple );

		List<String> l = new ArrayList<>();
		l.add("Simple 1");
		l.add("Slimeball");
		parameters.put("several", l);
		q = s.createQuery("from Simple s where s.name in (:several)");
		q.setProperties(parameters);
		assertTrue( q.list().get(0)==simple );

		parameters.put("stuff", l.toArray(new String[0]));
		q = s.createQuery("from Simple s where s.name in (:stuff)");
		q.setProperties(parameters);
		assertTrue( q.list().get(0)==simple );

		s.delete(simple);
		t.commit();
		s.close();
	}
