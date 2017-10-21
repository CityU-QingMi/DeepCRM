	@Test
	@TestForIssue(jiraKey = "")
	public void testUpdateDetachedChanged() {
		final Bar bar = new Bar( 2, "Bar" );
		final Foo foo = new Foo( 2, "Foo", bar );

		// this should generate versions
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.save( bar );
			session.save( foo );
		} );

		// this should generate a new version
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			foo.setName( "FooChanged" );
			session.update( foo );
		} );

		assertEquals( Integer.valueOf( 0 ), bar.getVersion() );
		assertEquals( Integer.valueOf( 1 ), foo.getVersion() );
	}
