	@Test
	public void testUpdateWithSubquery() {
		Session s = openSession();
		s.beginTransaction();

		// just checking parsing and syntax...
		s.createQuery( "update Human h set h.bodyWeight = h.bodyWeight + (select count(1) from IntegerVersioned)" ).executeUpdate();
		s.createQuery( "update Human h set h.bodyWeight = h.bodyWeight + (select count(1) from IntegerVersioned) where h.description = 'abc'" ).executeUpdate();

		s.getTransaction().commit();
		s.close();
	}
