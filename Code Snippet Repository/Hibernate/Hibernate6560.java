	@Test
	@TestForIssue(jiraKey = "")
	public void testFormula() throws SQLException {
		// use native SQL to insert, forcing whitespace to occur
		final Session s = openSession();
		final Connection connection = ((SessionImplementor)s).connection();
		final Statement statement = connection.createStatement();
		statement.execute("insert into EntityEnum (id) values(1)");

		s.getTransaction().begin();

		// ensure EnumType can do #fromName with the trimming
		List<EntityEnum> resultList = s.createQuery("select e from EntityEnum e").list();
		assertEquals( resultList.size(), 1 );
		assertEquals( resultList.get(0).getFormula(), Trimmed.A );

		statement.execute( "delete from EntityEnum" );

		s.getTransaction().commit();
		s.close();
	}
