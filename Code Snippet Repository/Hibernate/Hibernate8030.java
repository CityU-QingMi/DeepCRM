	@Test
	public void testSearchedCaseStatementFixture() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createQuery( "select case when p.name = 'Steve' then 'x' else 'y' end from Person p" )
				.list();

		t.commit();
		s.close();
	}
