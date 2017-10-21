	@Test
	public void testLoadExtendedByNormalCatchingWrongClassException2() {
		doInHibernate( this::sessionFactory, session -> {
			session.save( new MyEntity( "normal" ) );
			session.save( new ExtendedEntity( "extended", "ext" ) );
		});

		doInHibernate( this::sessionFactory, session -> {
			MyEntity user = session.byNaturalId( MyEntity.class ).using(
					"uid",
					"normal"
			).load();
			ExtendedEntity extendedMyEntity = session.byNaturalId(
					ExtendedEntity.class )
					.using( "uid", "extended" )
					.load();
			assertNotNull( user );
			assertNotNull( extendedMyEntity );
		} );

		// Temporarily change logging level for these two classes to DEBUG
		final Logger afelLogger = LogManager.getLogger(
				"org.hibernate.event.internal.AbstractFlushingEventListener" );
		final Logger epLogger = LogManager.getLogger(
				"org.hibernate.internal.util.EntityPrinter" );
		final Level afelLevel = afelLogger.getLevel();
		final Level epLevel = epLogger.getLevel();

		try {
			// this throws if logging level is set to debug
			doInHibernate( this::sessionFactory, session -> {

				afelLogger.setLevel( Level.DEBUG );
				epLogger.setLevel( Level.DEBUG );

				session.byNaturalId( ExtendedEntity.class ).using(
						"uid",
						"normal"
				).load();
			} );

		}
		finally {
			// set back previous logging level
			afelLogger.setLevel( afelLevel );
			epLogger.setLevel( epLevel );
		}
	}
