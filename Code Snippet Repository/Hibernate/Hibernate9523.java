	@Test
	public void testTimeZone() {

		connectionProvider.clear();
		doInHibernate( this::sessionFactory, s -> {
			Person person = new Person();
			person.id = 1L;
			s.persist( person );

		} );

		assertEquals( 1, connectionProvider.getPreparedStatements().size() );
		PreparedStatement ps = connectionProvider.getPreparedStatements()
				.get( 0 );
		try {
			verify( ps, times( 1 ) ).setTime( anyInt(), any( Time.class ) );
		}
		catch ( SQLException e ) {
			fail( e.getMessage() );
		}

		doInHibernate( this::sessionFactory, s -> {
			Person person = s.find( Person.class, 1L );
			assertEquals(
					0,
					person.createdOn.getTime() % TimeUnit.DAYS.toSeconds( 1 )
			);
		} );
	}
