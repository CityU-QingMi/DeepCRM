	@Test
	public void testBasicNoOptimizerUsageWithNegativeValues() {
		// test historic sequence behavior, where the initial values start at 1...
		SourceMock sequence = new SourceMock( -1, -1 );
		Optimizer optimizer = buildNoneOptimizer( -1, -1 );
		for ( int i = 1; i < 11; i++ ) {
			final Long next = ( Long ) optimizer.generate( sequence );
			assertEquals( -i, next.intValue() );
		}
		assertEquals( 10, sequence.getTimesCalled() );
		assertEquals( -10, sequence.getCurrentValue() );

		// As of HHH-11709 being fixed, Hibernate will use the value retrieved from the sequence,
		// rather than incrementing 1.
		sequence = new SourceMock( 0 );
		optimizer = buildNoneOptimizer( -1, 1 );
		for ( int i = 1; i < 11; i++ ) {
			final Long next = ( Long ) optimizer.generate( sequence );
			assertEquals( i-1, next.intValue() );
		}
		assertEquals( 10, sequence.getTimesCalled() ); // an extra time to get to 1 initially
		assertEquals( 9, sequence.getCurrentValue() );
	}
