	@TestForIssue( jiraKey = "" )
	@Test
	public void test() {
		Session s = openSession();
		s.getTransaction().begin();
		ATable aTable = new ATable( 1 );
		TableB tableB = new TableB(
			new TableBId( 1, "a", "b" )
		);
		aTable.getTablebs().add( tableB );
		tableB.setTablea( aTable );
		s.save( aTable );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		aTable = (ATable) s.createQuery( "select distinct	tablea from ATable tablea LEFT JOIN FETCH tablea.tablebs " ).uniqueResult();
		assertEquals( new Integer( 1 ), aTable.getFirstId() );
		assertEquals( 1, aTable.getTablebs().size() );
		tableB = aTable.getTablebs().get( 0 );
		assertSame( aTable, tableB.getTablea() );
		assertEquals( new Integer( 1 ), tableB.getId().getFirstId() );
		assertEquals( "a", tableB.getId().getSecondId() );
		assertEquals( "b", tableB.getId().getThirdId() );
		s.close();
	}
