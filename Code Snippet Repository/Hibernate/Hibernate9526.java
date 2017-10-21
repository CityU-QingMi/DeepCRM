	@Test
	public void testTimeZone() {
		doInHibernate( this::sessionFactory, session -> {
			Person person = new Person();
			person.id = 1L;
			long y2kMillis = LocalDateTime.of( 2000, 1, 1, 0, 0, 0 )
					.atZone( ZoneId.of( "UTC" ) )
					.toInstant()
					.toEpochMilli();
			assertEquals( 946684800000L, y2kMillis );

			person.createdOn = new Timestamp( y2kMillis );
			session.persist( person );

		} );
		doInHibernate( this::sessionFactory, s -> {
			s.doWork( connection -> {
				try ( Statement st = connection.createStatement() ) {
					try ( ResultSet rs = st.executeQuery(
							"SELECT to_char(createdon, 'YYYY-MM-DD HH24:MI:SS.US') " +
									"FROM person" ) ) {
						while ( rs.next() ) {
							String timestamp = rs.getString( 1 );
							assertEquals( expectedTimestampValue(), timestamp );
						}
					}
				}
			} );
		} );
	}
