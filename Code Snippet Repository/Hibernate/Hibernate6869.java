	public void testCriteriaNullsFirstLast() {
		Session session = openSession();

		// Populating database with test data.
		session.getTransaction().begin();
		Zoo zoo1 = new Zoo( null );
		Zoo zoo2 = new Zoo( "Warsaw ZOO" );
		session.persist( zoo1 );
		session.persist( zoo2 );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		Criteria criteria = session.createCriteria( Zoo.class );
		criteria.addOrder( org.hibernate.criterion.Order.asc( "name" ).nulls( NullPrecedence.LAST ) );
		Iterator<Zoo> iterator = (Iterator<Zoo>) criteria.list().iterator();
		Assert.assertEquals( zoo2.getName(), iterator.next().getName() );
		Assert.assertNull( iterator.next().getName() );
		session.getTransaction().commit();

		session.clear();

		// Cleanup data.
		session.getTransaction().begin();
		session.delete( zoo1 );
		session.delete( zoo2 );
		session.getTransaction().commit();

		session.close();
	}
