	@Test
	public void testFindBySQLProperties() throws HibernateException, SQLException {
		Session session = openSession();
		session.beginTransaction();
		for ( Object entity : session.createQuery( "from Category" ).list() ) {
			session.delete( entity );
		}

		Category s = new Category();
		s.setName( String.valueOf( nextLong++ ) );
		session.save( s );

		s = new Category();
		s.setName( "WannaBeFound" );
		session.flush();

		Query query = session.createSQLQuery( "select {category.*} from category {category} where {category}.name = :name" )
				.addEntity( "category", Category.class );

		query.setProperties( s );
		//query.setParameter("name", s.getName());

		query.list();

		query = session.createSQLQuery( "select {category.*} from category {category} where {category}.name in (:names)" )
				.addEntity( "category", Category.class );
		String[] str = new String[] { "WannaBeFound", "NotThere" };
		query.setParameterList( "names", str );
		query.uniqueResult();

		query = session.createSQLQuery( "select {category.*} from category {category} where {category}.name in :names" )
				.addEntity( "category", Category.class );
		query.setParameterList("names", str);
		query.uniqueResult();

		query = session.createSQLQuery( "select {category.*} from category {category} where {category}.name in (:names)" )
				.addEntity( "category", Category.class );
		str = new String[] { "WannaBeFound" };
		query.setParameterList( "names", str );
		query.uniqueResult();

		query = session.createSQLQuery( "select {category.*} from category {category} where {category}.name in :names" )
				.addEntity( "category", Category.class );
		query.setParameterList( "names", str );
		query.uniqueResult();

		session.getTransaction().commit();
		session.close();
	}
