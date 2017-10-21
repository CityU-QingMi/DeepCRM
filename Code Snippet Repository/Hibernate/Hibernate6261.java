	@Test
	@SkipForDialect(value = Oracle10gDialect.class, comment = "")
	public void testTemporalType() throws Exception {


		Flight airFrance = doInHibernate( this::sessionFactory, session -> {
			Flight _airFrance = new Flight();
			_airFrance.setId( Long.valueOf( 747 ) );
			_airFrance.setName( "Paris-Amsterdam" );
			_airFrance.setDuration( Long.valueOf( 10 ) );
			_airFrance.setDepartureDate( Date.from(LocalDate.of( 2005, 06, 21 ).atStartOfDay(
					ZoneId.systemDefault()).toInstant()) );
			_airFrance.setAlternativeDepartureDate( new GregorianCalendar( 2006, 02, 03, 10, 00 ) );
			_airFrance.getAlternativeDepartureDate().setTimeZone( TimeZone.getTimeZone( "GMT" ) );
			_airFrance.setBuyDate( new java.sql.Timestamp( 122367443 ) );
			_airFrance.setFactor( 25 );
			session.persist( _airFrance );

			return _airFrance;
		} );

		doInHibernate( this::sessionFactory, session -> {
			Query q = session.createQuery( "from Flight f where f.departureDate = :departureDate" );
			q.setParameter( "departureDate", airFrance.getDepartureDate(), StandardBasicTypes.DATE );
			Flight copyAirFrance = (Flight) q.uniqueResult();
			assertNotNull( copyAirFrance );
			assertEquals(
					Date.from(LocalDate.of( 2005, 06, 21 ).atStartOfDay(
							ZoneId.systemDefault()).toInstant()),
					copyAirFrance.getDepartureDate()
			);
			assertEquals( df.format( airFrance.getBuyDate() ), df.format( copyAirFrance.getBuyDate() ) );

			session.delete( copyAirFrance );
		} );
	}
