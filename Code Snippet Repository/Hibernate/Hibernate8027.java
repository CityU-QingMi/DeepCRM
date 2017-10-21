	@Test
	public void testSimpleCaseStatementFixture() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createQuery( "select case p.name when 'Steve' then 'x' else 'y' end from Person p" )
				.list();

		t.commit();
		s.close();
	}
