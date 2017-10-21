	@Test
	public void testLoadingNonInverseSide() {
		prepareTestData();

		sessionFactory().getStatistics().clear();
		CollectionStatistics userGroupStats = sessionFactory().getStatistics()
				.getCollectionStatistics( User.class.getName() + ".groups" );
		CollectionStatistics groupUserStats = sessionFactory().getStatistics()
				.getCollectionStatistics( Group.class.getName() + ".users" );

		Interceptor testingInterceptor = new EmptyInterceptor() {
			@Override
            public String onPrepareStatement(String sql) {
				// ugh, this is the best way I could come up with to assert this.
				// unfortunately, this is highly dependent on the dialect and its
				// outer join fragment.  But at least this wil fail on the majority
				// of dialects...
				Assert.assertFalse(
						"batch load of many-to-many should use inner join",
						sql.toLowerCase(Locale.ROOT).contains( "left outer join" )
				);
				return super.onPrepareStatement( sql );
			}
		};

		Session s = openSession( testingInterceptor );
		s.beginTransaction();
		List users = s.createQuery( "from User u" ).list();
		User user = ( User ) users.get( 0 );
		assertTrue( Hibernate.isInitialized( user ) );
		assertTrue( Hibernate.isInitialized( user.getGroups() ) );
		user = ( User ) users.get( 1 );
		assertTrue( Hibernate.isInitialized( user ) );
		assertTrue( Hibernate.isInitialized( user.getGroups() ) );
		assertEquals( 1, userGroupStats.getFetchCount() ); // should have been just one fetch (the batch fetch)
		assertEquals( 1, groupUserStats.getFetchCount() ); // should have been just one fetch (the batch fetch)
		s.getTransaction().commit();
		s.close();

	}
