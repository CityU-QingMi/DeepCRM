	@Test
	public void testCriteriaDefaultNullOrdering() {
		Session session = openSession();

		// Populating database with test data.
		session.getTransaction().begin();
		Monkey monkey1 = new Monkey();
		monkey1.setName( null );
		Monkey monkey2 = new Monkey();
		monkey2.setName( "Berlin ZOO" );
		session.persist( monkey1 );
		session.persist( monkey2 );
		session.getTransaction().commit();

		session.getTransaction().begin();
		Criteria criteria = session.createCriteria( Monkey.class );
		criteria.addOrder( org.hibernate.criterion.Order.asc( "name" ) ); // Should order by NULLS LAST.
		Assert.assertEquals( Arrays.asList( monkey2, monkey1 ), criteria.list() );
		session.getTransaction().commit();

		session.clear();

		// Cleanup data.
		session.getTransaction().begin();
		session.delete( monkey1 );
		session.delete( monkey2 );
		session.getTransaction().commit();

		session.close();
	}
