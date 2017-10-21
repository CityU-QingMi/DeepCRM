	@Test
	@TestForIssue( jiraKey = "" )
	public void testNullReplacementOnExtraction() {
		Session session = openSession();
		session.beginTransaction();
		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						connection.createStatement().execute( "insert into the_entity(id, sex) values (1, null)" );
					}
				}
		);
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		// at this point TheEntity.sex is null in the database
		// lets load it and make sure that the converter is given a chance to adjust that to UNKNOWN...
		TheEntity theEntity = (TheEntity) session.get( TheEntity.class, 1 );
		session.getTransaction().commit();
		session.close();

		assertEquals( Sex.UNKNOWN, theEntity.sex );

		session = openSession();
		session.beginTransaction();
		session.delete( theEntity );
		session.getTransaction().commit();
		session.close();
	}
