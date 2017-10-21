	@Test
	@TestForIssue(jiraKey = "")
	public void testUpdateDetachedUnchangedAndChanged() {
		final Bar bar = new Bar( 3, "Bar" );
		final Foo foo = new Foo( 3, "Foo", bar );

		// this should generate versions
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.save( bar );
			session.save( foo );
		} );

		// this shouldn't generate a new version.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.update( foo );
		} );

		// this should generate a new version
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			foo.setName( "FooChanged" );
			session.update( foo );
		} );

		assertEquals( Integer.valueOf( 0 ), bar.getVersion() );
		assertEquals( Integer.valueOf( 1 ), foo.getVersion() );
	}
