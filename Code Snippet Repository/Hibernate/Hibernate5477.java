	@Test
	public void testSubsequentPooledLoOptimizerUsage() {
		// test the pooled-lo optimizer in situation where the sequence is already beyond its initial value on init.
		//		cheat by telling the sequence to start with 1000
		final SourceMock sequence = new SourceMock( 1001, 3, 5 );
		//		but tell the optimizer the start-with is 1
		final Optimizer optimizer = buildPooledLoOptimizer( 1, 3 );

		assertEquals( 5, sequence.getTimesCalled() );
		assertEquals( 1001, sequence.getCurrentValue() );

		// should "clock over" immediately
		Long next = ( Long ) optimizer.generate( sequence );
		assertEquals( (1001+3), next.intValue() );
		assertEquals( (5+1), sequence.getTimesCalled() );
		assertEquals( (1001+3), sequence.getCurrentValue() );

		next = ( Long ) optimizer.generate( sequence );
		assertEquals( (1001+4), next.intValue() );
		assertEquals( (5+1), sequence.getTimesCalled() );
		assertEquals( (1001+3), sequence.getCurrentValue() );

		next = ( Long ) optimizer.generate( sequence );
		assertEquals( (1001+5), next.intValue() );
		assertEquals( (5+1), sequence.getTimesCalled() );
		assertEquals( (1001+3), sequence.getCurrentValue() );

//		// force a "clock over"
		next = ( Long ) optimizer.generate( sequence );
		assertEquals( (1001+6), next.intValue() );
		assertEquals( (5+2), sequence.getTimesCalled() );
		assertEquals( (1001+6), sequence.getCurrentValue() );
	}
