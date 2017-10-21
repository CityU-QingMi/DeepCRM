	public void testLongInElementsByCriteria() {
		Session session = openSession();
		Transaction t = session.beginTransaction();

		StateProvince beijing = new StateProvince();
		beijing.setIsoCode( "100089" );
		beijing.setName( "beijing" );
		session.persist( beijing );
		session.flush();
		session.clear();

		Criteria criteria = session.createCriteria( StateProvince.class );
		criteria.add( Restrictions.in( "id" , createLotsOfElements() ) );
		List list = criteria.list();
		session.flush();
		session.clear();
		assertEquals( 1 , list.size() );
		session.delete( beijing );
		t.commit();
		session.close();

	}
