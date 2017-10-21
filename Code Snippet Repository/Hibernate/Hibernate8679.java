	@Test
	public void testEmbeddedCompositeProperties() throws HibernateException, SQLException {
		Session session = openSession();
		session.beginTransaction();
		Single s = new Single();
		s.setId("my id");
		s.setString("string 1");
		session.save(s);
		session.getTransaction().commit();

		session = openSession();
		session.beginTransaction();

		SQLQuery query = session.createSQLQuery( "select {sing.*} from Single {sing}" ).addEntity( "sing", Single.class );
		List list = query.list();
		assertTrue(list.size()==1);

		session.clear();

		query = session.createSQLQuery( "select {sing.*} from Single {sing} where sing.id = ?" ).addEntity( "sing", Single.class );
	   	query.setString(0, "my id");
	   	list = query.list();
		assertTrue(list.size()==1);

		session.clear();

		query = session.createSQLQuery( "select s.id as {sing.id}, s.string_ as {sing.string}, s.prop as {sing.prop} from Single s where s.id = ?" )
			   .addEntity( "sing", Single.class );
		query.setString(0, "my id");
	   	list = query.list();
		assertTrue(list.size()==1);

		session.clear();

		query = session.createSQLQuery( "select s.id as {sing.id}, s.string_ as {sing.string}, s.prop as {sing.prop} from Single s where s.id = ?" )
			   .addEntity( "sing", Single.class );
		query.setString(0, "my id");
		list = query.list();

		assertTrue(list.size()==1);

		session.getTransaction().commit();
		session.close();
	}
