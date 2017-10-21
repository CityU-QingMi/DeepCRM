	@Test
	public void testReusingQueryWithNewParameterValues() throws Exception {
		try (Session session = openSession()) {
			Collection<Long> ids = new ArrayList<>();
			Query q = session.createQuery( "select id from Person where id in (:ids) order by id" );
			for ( int i = 0; i < 10; i++ ) {
				ids.add( Long.valueOf( i ) );
			}
			q.setParameterList( "ids", ids );
			q.list();

			ids.clear();
			for ( int i = 10; i < 20; i++ ) {
				ids.add( Long.valueOf( i ) );
			}
			// reuse the same query, but set new collection parameter
			q.setParameterList( "ids", ids );
			List<Long> foundIds = q.list();

			assertThat( "Wrong number of results", foundIds.size(), is( ids.size() ) );
			assertThat( foundIds, is( ids ) );
		}
	}
