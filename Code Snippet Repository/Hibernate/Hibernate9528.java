	@Test
	@TestForIssue( jiraKey = "" )
	public void testTimeZone() {
		TimeZone old = TimeZone.getDefault();
		try {
			// The producer (MySQL) Berlin and returns 1980-01-01
			TimeZone jdbcTimeZone = TimeZone.getTimeZone( "Europe/Berlin" );
			TimeZone.setDefault( jdbcTimeZone );

			//hibernate.connection.url jdbc:mysql://localhost/hibernate_orm_test
			doInHibernateSessionBuilder( () -> sessionFactory().withOptions().jdbcTimeZone( TIME_ZONE ), s -> {
				Person person = new Person();
				person.id = 1L;
				s.persist( person );
			} );

			doInHibernateSessionBuilder( () -> sessionFactory().withOptions().jdbcTimeZone( TIME_ZONE ), s -> {
				Person person = s.find( Person.class, 1L );
				assertEquals( LocalDate.of( 2017, 3, 7 ), person.createdOn );
			} );
		}
		finally {
			TimeZone.setDefault( old );
		}
	}
