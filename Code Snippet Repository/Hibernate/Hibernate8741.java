	@Test
	@RequiresDialect({ Oracle8iDialect.class })
	public void testOracleSkipLocked() {

		doInHibernate( this::sessionFactory, session -> {
			for ( long i = 1; i <= 10; i++ ) {
				BatchJob batchJob = new BatchJob();
				batchJob.setId( i );
				session.persist( batchJob );
			}
		} );

		doInHibernate( this::sessionFactory, session -> {
			List<BatchJob> firstFive = nextFiveBatchJobs( session );

			assertEquals( 5, firstFive.size() );
			assertTrue( firstFive.stream().map( BatchJob::getId ).collect( Collectors.toList() )
								.containsAll( Arrays.asList( 1L, 2L, 3L, 4L, 5L ) ) );

			executeSync( () -> {
				doInHibernate( this::sessionFactory, _session -> {
					List<BatchJob> nextFive = nextFiveBatchJobs( _session );

					assertEquals( 0, nextFive.size() );

					nextFive = nextFiveBatchJobs( _session, 10 );

					assertTrue( nextFive.stream().map( BatchJob::getId ).collect( Collectors.toList() )
										.containsAll( Arrays.asList( 6L, 7L, 8L, 9L, 10L ) ) );
				} );
			} );

		} );
	}
