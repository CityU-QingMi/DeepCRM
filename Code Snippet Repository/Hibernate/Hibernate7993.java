	public void testInsertWithSelectListUsingJoins() {
		// this is just checking parsing and syntax...
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "insert into Animal (description, bodyWeight) select h.description, h.bodyWeight from Human h where h.mother.mother is not null" ).executeUpdate();
		s.createQuery( "insert into Animal (description, bodyWeight) select h.description, h.bodyWeight from Human h join h.mother m where m.mother is not null" ).executeUpdate();
		s.createQuery( "delete from Animal" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
