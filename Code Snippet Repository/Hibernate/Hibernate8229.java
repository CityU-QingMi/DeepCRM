	@Test
	@TestForIssue(jiraKey = "")
	public void testMultipleUpdates() {
		Session s = openSession();
		s.getTransaction().begin();
		Query query = s.createQuery( "SELECT s FROM PrimitiveCharacterArrayIdTest$DemoEntity s" );
		List<DemoEntity> results = (List<DemoEntity>) query.list();
		results.get( 0 ).name = "Different 0";
		results.get( 1 ).name = "Different 1";
		final String lastResultName = results.get( 0 ).name;
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		query = s.createQuery( "SELECT s FROM PrimitiveCharacterArrayIdTest$DemoEntity s" );
		results = (List<DemoEntity>) query.list();
		final Set<String> names = new HashSet<String>(  );
		for ( DemoEntity entity : results ) {
			names.add( entity.name );
		}
		assertTrue( names.contains( "Different 0" ) );
		assertTrue( names.contains( "Different 1" ) );
		assertTrue( names.contains( lastResultName ) );
		s.getTransaction().commit();
		s.close();
	}
