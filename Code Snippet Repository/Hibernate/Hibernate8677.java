	@Test
	@SkipForDialect( { HSQLDialect.class, PostgreSQL81Dialect.class, PostgreSQLDialect.class } )
	public void testEscapedJDBC() throws HibernateException, SQLException {
		Session session = openSession();
		session.beginTransaction();
		for ( Object entity : session.createQuery( "from A" ).list() ) {
			session.delete( entity );
		}
		A savedA = new A();
		savedA.setName("Max");
		session.save( savedA );

		B savedB = new B();
		session.save( savedB );
		session.flush();

		int count = session.createQuery("from A").list().size();
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();

		Query query;
		if( getDialect() instanceof TimesTenDialect) {
            // TimesTen does not permit general expressions (like UPPER) in the second part of a LIKE expression,
            // so we execute a similar test 
            query = session.createSQLQuery("select identifier_column as {a.id}, clazz_discriminata as {a.class}, count_ as {a.count}, name as {a.name} from TA where {fn ucase(name)} like 'MAX'" )
					.addEntity( "a", A.class );
        }
		else {
            query = session.createSQLQuery( "select identifier_column as {a.id}, clazz_discriminata as {a.class}, count_ as {a.count}, name as {a.name} from TA where {fn ucase(name)} like {fn ucase('max')}" )
					.addEntity( "a", A.class );
        }
		List list = query.list();

		assertNotNull(list);
		assertEquals( 1, list.size() );
		session.getTransaction().commit();
		session.close();
	}
