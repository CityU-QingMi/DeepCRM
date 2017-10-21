	@Test
	public void testSearchedCaseStatementWithParamResult() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createQuery( "select case when p.name = 'Steve' then :opt1 else p.name end from Person p" )
				.setString( "opt1", "x" )
				.list();

		t.commit();
		s.close();
	}
