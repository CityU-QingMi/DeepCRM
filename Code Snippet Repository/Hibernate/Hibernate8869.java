	@Test
	public void testLoadExtendedByNormalCatchingWrongClassException() {
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

		doInHibernate( this::sessionFactory, session -> {
			session.byNaturalId( ExtendedEntity.class ).using(
					"uid",
					"normal"
			).load();
		} );

	}
