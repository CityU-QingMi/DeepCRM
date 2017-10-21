	@Test
	public void testCollectionQuery() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		if ( !(getDialect() instanceof MySQLDialect) && !(getDialect() instanceof SAPDBDialect) && !(getDialect() instanceof MckoiDialect) ) {
			s.createQuery( "FROM Master m WHERE NOT EXISTS ( FROM m.details d WHERE NOT d.i=5 )" ).iterate();
			s.createQuery( "FROM Master m WHERE NOT 5 IN ( SELECT d.i FROM m.details AS d )" ).iterate();
		}
		s.createQuery( "SELECT m FROM Master m JOIN m.details d WHERE d.i=5" ).iterate();
		s.createQuery( "SELECT m FROM Master m JOIN m.details d WHERE d.i=5" ).list();
		s.createQuery( "SELECT m.id FROM Master AS m JOIN m.details AS d WHERE d.i=5" ).list();
		t.commit();
		s.close();
	}
