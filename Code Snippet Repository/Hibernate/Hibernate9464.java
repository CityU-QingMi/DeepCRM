	@Test
	public void testExpandedEntityMapping() {
		Session session = openSession();
		session.beginTransaction();
		SQLQuery query = (SQLQuery) session.getNamedQuery( "query-person" );
		query.setResultSetMapping( "person-entity-expanded" );
		query.list();
		session.getTransaction().commit();
		session.close();
	}
