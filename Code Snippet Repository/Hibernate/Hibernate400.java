	@Test
	public void testProxyClassLoader() {

		//tag::entity-proxy-persist-mapping[]
		doInHibernate( this::sessionFactory, session -> {
			Book book = new Book();
			book.setId( 1L );
			book.setTitle( "High-Performance Java Persistence" );
			book.setAuthor( "Vlad Mihalcea" );

			session.persist( book );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Identifiable book = session.getReference( Book.class, 1L );

			assertTrue(
				"Loaded entity is not an instance of the proxy interface",
				Identifiable.class.isInstance( book )
			);
			assertFalse(
				"Proxy class was not created",
				Book.class.isInstance( book )
			);
		} );
		//end::entity-proxy-persist-mapping[]
	}
