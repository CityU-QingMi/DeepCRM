	@Test
	public void testBasicEntityMapping() {
		Session session = openSession();
		session.beginTransaction();
		SQLQuery query = (SQLQuery) session.getNamedQuery( "query-person" );
		query.setResultSetMapping( "person-entity-basic" );
		query.list();
		session.getTransaction().commit();
		session.close();
	}
