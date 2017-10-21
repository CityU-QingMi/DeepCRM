	@Test
	public void testDoubleAliasing() throws HibernateException, SQLException {
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
		String sql = "select a.identifier_column as {a1.id}, " +
				"    a.clazz_discriminata as {a1.class}, " +
				"    a.count_ as {a1.count}, " +
				"    a.name as {a1.name}, " +
				"    b.identifier_column as {a2.id}, " +
				"    b.clazz_discriminata as {a2.class}, " +
				"    b.count_ as {a2.count}, " +
				"    b.name as {a2.name} " +
				"from TA a, TA b " +
				"where a.identifier_column = b.identifier_column";
		Query query = session.createSQLQuery( sql ).addEntity( "a1", A.class ).addEntity( "a2", A.class );
		List list = query.list();

		assertNotNull(list);
		assertEquals( 2, list.size() );
		session.getTransaction().commit();
		session.close();
	}
