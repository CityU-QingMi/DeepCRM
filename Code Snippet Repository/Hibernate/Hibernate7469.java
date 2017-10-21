	@Test
	@TestForIssue( jiraKey = "" )
	public void testNullReplacementOnBinding() {
		TheEntity theEntity = new TheEntity( 1 );

		Session session = openSession();
		session.beginTransaction();
		// at this point TheEntity.sex is null
		// lets make sure that the converter is given a chance to adjust that to UNKNOWN...
		session.save( theEntity );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						ResultSet rs = connection.createStatement().executeQuery( "select sex from the_entity where id=1" );
						try {
							if ( !rs.next() ) {
								throw new RuntimeException( "Could not locate inserted row" );
							}

							String sexDbValue = rs.getString( 1 );

							if ( rs.next() ) {
								throw new RuntimeException( "Found more than one row" );
							}

							assertEquals( Sex.UNKNOWN.name().toLowerCase( Locale.ENGLISH ), sexDbValue );
						}
						finally {
							rs.close();
						}
					}
				}
		);
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( theEntity );
		session.getTransaction().commit();
		session.close();
	}
