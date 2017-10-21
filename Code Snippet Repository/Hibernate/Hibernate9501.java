	@Test
	public void basicStreamTest() {
		Session session = openSession();
		session.getTransaction().begin();

		// mainly we want to make sure that closing the Stream releases the ScrollableResults too
		assertThat( ( (SessionImplementor) session ).getJdbcCoordinator().getLogicalConnection().getResourceRegistry().hasRegisteredResources(), is( false ) );
		final Stream<MyEntity> stream = session.createQuery( "from MyEntity", MyEntity.class ).stream();
		assertThat( ( (SessionImplementor) session ).getJdbcCoordinator().getLogicalConnection().getResourceRegistry().hasRegisteredResources(), is( true ) );
		stream.forEach( System.out::println );
		assertThat( ( (SessionImplementor) session ).getJdbcCoordinator().getLogicalConnection().getResourceRegistry().hasRegisteredResources(), is( true ) );
		stream.close();
		assertThat( ( (SessionImplementor) session ).getJdbcCoordinator().getLogicalConnection().getResourceRegistry().hasRegisteredResources(), is( false ) );

		session.getTransaction().commit();
		session.close();
	}
