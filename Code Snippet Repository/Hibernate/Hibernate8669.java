	@Test
	public void testFindBySQLSimpleByDiffSessions() throws Exception {
		Session session = openSession();
		session.beginTransaction();
		Category s = new Category();
		s.setName(String.valueOf(nextLong++));
		session.save(s);
		session.getTransaction().commit();
		session.close();

		if ( getDialect() instanceof MySQLDialect ) {
			return;
		}

		session = openSession();
		session.beginTransaction();

		Query query = session.createSQLQuery( "select s.category_key_col as {category.id}, s.name as {category.name}, s.\"assign-able-id\" as {category.assignable} from {category} s" )
				.addEntity( "category", Category.class );
		List list = query.list();

		assertNotNull( list );
		assertTrue( list.size() > 0 );
		assertTrue( list.get( 0 ) instanceof Category );

		// How do we handle objects that does not have id property (such as Simple ?)
		// How do we handle objects with composite id's ? (such as Single)
		session.getTransaction().commit();
		session.close();
	}
