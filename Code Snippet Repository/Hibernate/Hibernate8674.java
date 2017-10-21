	@Test
	public void testFindBySQLStar() throws HibernateException, SQLException {
		Session session = openSession();
		session.beginTransaction();
		for ( Object entity : session.createQuery( "from Assignable" ).list() ) {
			session.delete( entity );
		}
		for ( Object entity : session.createQuery( "from Category" ).list() ) {
			session.delete( entity );
		}
		for ( Object entity : session.createQuery( "from Simple" ).list() ) {
			session.delete( entity );
		}
		for ( Object entity : session.createQuery( "from A" ).list() ) {
			session.delete( entity );
		}

		Category s = new Category();
		s.setName(String.valueOf(nextLong++));
		session.save( s );

		Simple simple = new Simple( Long.valueOf(nextLong++) );
		simple.init();
		session.save( simple );

		A a = new A();
		session.save(a);

		B b = new B();
		session.save( b );
		session.flush();

		session.createSQLQuery( "select {category.*} from category {category}" ).addEntity( "category", Category.class ).list();
		session.createSQLQuery( "select {simple.*} from SimpleEntity {simple}" ).addEntity( "simple", Simple.class ).list();
		session.createSQLQuery( "select {a.*} from TA {a}" ).addEntity( "a", A.class ).list();

		session.getTransaction().commit();
		session.close();
	}
