	@Test
	public void testPoolsClosed() {
		assertDefinedPools(); // zero-length-vararg used as parameter
		StandardServiceRegistry serviceRegistry = buildServiceRegistry( "pool-one" );
		ConnectionProvider providerOne = serviceRegistry.getService( ConnectionProvider.class );
		assertDefinedPools( "pool-one" );


		StandardServiceRegistry serviceRegistryTwo = buildServiceRegistry( "pool-two" );
		ConnectionProvider providerTwo = serviceRegistryTwo.getService( ConnectionProvider.class );
		assertDefinedPools( "pool-one", "pool-two" );
		
		StandardServiceRegistryBuilder.destroy( serviceRegistry );
		assertDefinedPools( "pool-two" );

		StandardServiceRegistryBuilder.destroy( serviceRegistryTwo );
		assertDefinedPools();
	}
