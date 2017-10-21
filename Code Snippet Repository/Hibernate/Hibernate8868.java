	@Test
	public void testLoadExtendedByNormal() {
		doInHibernate( this::sessionFactory, session -> {
			session.save( new MyEntity( "base" ) );
			session.save( new ExtendedEntity( "extended", "ext" ) );
		});

		doInHibernate( this::sessionFactory, session -> {
			// Sanity check, ensure both entities is accessible.
			MyEntity user = session.byNaturalId( MyEntity.class ).using(
					"uid",
					"base"
			).load();
			ExtendedEntity extendedMyEntity = session.byNaturalId(
					ExtendedEntity.class )
					.using( "uid", "extended" )
					.load();
			assertNotNull( user );
			assertNotNull( extendedMyEntity );
		} );

		doInHibernate( this::sessionFactory, session -> {
			// This throws WrongClassException, since MyEntity was found using the ID, but we wanted ExtendedEntity.
			ExtendedEntity user = session.byNaturalId( ExtendedEntity.class )
					.using( "uid", "base" )
					.load();
			assertNull( user );
		} );
	}
