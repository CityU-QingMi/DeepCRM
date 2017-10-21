	@Test
	public void testRecoveredPooledOptimizerUsage() {
		final SourceMock sequence = new SourceMock( 1, 3 );
		final Optimizer optimizer = buildPooledOptimizer( 1, 3 );

		assertEquals( 0, sequence.getTimesCalled() );
		assertEquals( -1, sequence.getCurrentValue() );

		Long next = ( Long ) optimizer.generate( sequence );
		assertEquals( 1, next.intValue() );
		assertEquals( 2, sequence.getTimesCalled() );
		assertEquals( 4, sequence.getCurrentValue() );

		// app ends, and starts back up (we should "lose" only 2 and 3 as id values)
		final Optimizer optimizer2 = buildPooledOptimizer( 1, 3 );
		next = ( Long ) optimizer2.generate( sequence );
		assertEquals( 5, next.intValue() );
		assertEquals( 3, sequence.getTimesCalled() );
		assertEquals( 7, sequence.getCurrentValue() );
	}
