	public void testUpdateOnManyToOne() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createQuery( "update Animal a set a.mother = null where a.id = 2" ).executeUpdate();
		if ( ! ( getDialect() instanceof MySQLDialect ) ) {
			// MySQL does not support (even un-correlated) subqueries against the update-mutating table
			s.createQuery( "update Animal a set a.mother = (from Animal where id = 1) where a.id = 2" ).executeUpdate();
		}

		t.commit();
		s.close();
	}
