	@Test
	public void testBasicPooledLoOptimizerUsage() {
		final SourceMock sequence = new SourceMock( 1, 3 );
		final Optimizer optimizer = buildPooledLoOptimizer( 1, 3 );

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

//		// force a "clock over"
		next = ( Long ) optimizer.generate( sequence );
		assertEquals( 4, next.intValue() );
		assertEquals( 2, sequence.getTimesCalled() );
		assertEquals( (1+3), sequence.getCurrentValue() );
	}
