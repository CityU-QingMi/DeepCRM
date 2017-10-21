	@Test
	public void testBasicPooledThreadLocalLoOptimizerUsage() {
		final SourceMock sequence = new SourceMock( 1, 50 ); // pass 5000 to match default for PooledThreadLocalLoOptimizer.THREAD_LOCAL_BLOCK_SIZE
		final Optimizer optimizer = buildPooledThreadLocalLoOptimizer( 1, 50 );

		assertEquals( 0, sequence.getTimesCalled() );
		assertEquals( -1, sequence.getCurrentValue() );

		Long next = ( Long ) optimizer.generate( sequence );
		assertEquals( 1, next.intValue() );
		assertEquals( 1, sequence.getTimesCalled() );
		assertEquals( 1, sequence.getCurrentValue() );

		next = ( Long ) optimizer.generate( sequence );
		assertEquals( 2, next.intValue() );
		assertEquals( 1, sequence.getTimesCalled() );
		assertEquals( 1, sequence.getCurrentValue() );

		next = ( Long ) optimizer.generate( sequence );
		assertEquals( 3, next.intValue() );
		assertEquals( 1, sequence.getTimesCalled() );
		assertEquals( 1, sequence.getCurrentValue() );

		for( int looper = 0; looper < 51; looper++) {
			next = ( Long ) optimizer.generate( sequence );
		}

		assertEquals( 3 + 51, next.intValue() );
		assertEquals( 2, sequence.getTimesCalled() );
		assertEquals( 51, sequence.getCurrentValue() );

	}
