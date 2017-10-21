	public void testLongInElementsByHQL() {
		Session session = openSession();
		Transaction t = session.beginTransaction();

		StateProvince beijing = new StateProvince();
		beijing.setIsoCode( "100089" );
		beijing.setName( "beijing" );
		session.persist( beijing );
		session.flush();
		session.clear();

		Query query = session
				.createQuery( "from org.hibernate.test.hql.StateProvince sp where sp.id in ( :idList )" );
		query.setParameterList( "idList" , createLotsOfElements() );
		List list = query.list();
		session.flush();
		session.clear();
		assertEquals( 1 , list.size() );
		session.delete( beijing );
		t.commit();
		session.close();

	}
