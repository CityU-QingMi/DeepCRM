	@Test
	public void testLoadExtendedByNormal() {
		doInHibernate( this::sessionFactory, session -> {
			MyEntity user = session.byNaturalId( MyEntity.class ).using(
				"uid",
				"base"
			).load();
			ExtendedEntity extendedMyEntity = session.byNaturalId( ExtendedEntity.class )
					.using( "uid", "extended" )
					.load();
			assertNotNull( user );
			assertNotNull( extendedMyEntity );
		});

		doInHibernate( this::sessionFactory, session -> {
			ExtendedEntity user = session.byNaturalId( ExtendedEntity.class )
				.using( "uid", "base" )
				.load();
			assertNull( user );
		});
	}
