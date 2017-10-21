	@Test
	@TestForIssue( jiraKey = "" )
	@SkipForDialect( value = MySQLDialect.class, comment = "" )
	public void testDeleteMemberOf() {
		final String qry = "delete Attrvalue aval where aval.id in ( "
				+ "select val2.id from Employee e, Employeegroup eg, Attrset aset, Attrvalue val2 "
				+ "where eg.id = e.employeegroup.id " + "and aset.id = e.attrset.id "
				+ "and val2 member of aset.attrvalues)";
		Session s = openSession();
		s.getTransaction().begin();
		s.createQuery( qry ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
