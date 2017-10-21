	@Test
	@TestForIssue(jiraKey = "")
	public void testMultipleDeletions() {
		Session s = openSession();
		s.getTransaction().begin();
		Query query = s.createQuery( "SELECT s FROM CharacterArrayIdTest$DemoEntity s" );
		List results = query.list();
		s.delete( results.get( 0 ) );
		s.delete( results.get( 1 ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		query = s.createQuery( "SELECT s FROM CharacterArrayIdTest$DemoEntity s" );
		assertEquals( 1, query.list().size() );
		s.getTransaction().commit();
		s.close();
	}
