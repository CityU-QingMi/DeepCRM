	private void componentTest(String sql) throws SQLException {
	    Componentizable c = setupComponentData();

		Session session = openSession();
		session.beginTransaction();
		SQLQuery q = session.createSQLQuery( sql ).addEntity( "comp", Componentizable.class );
	    List list = q.list();
	    assertEquals( list.size(), 1 );

	    Componentizable co = (Componentizable) list.get(0);
	    assertEquals( c.getNickName(), co.getNickName() );
	    assertEquals( c.getComponent().getName(), co.getComponent().getName() );
	    assertEquals( c.getComponent().getSubComponent().getSubName(), co.getComponent().getSubComponent().getSubName() );

	    session.delete( co );
		session.getTransaction().commit();
	    session.close();
    }
