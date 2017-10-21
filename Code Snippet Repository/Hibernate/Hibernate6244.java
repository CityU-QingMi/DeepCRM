	@Test
	public void testStats() {
		assertEquals( 0, stats.getExecutionTotalTime() );
		assertEquals( Long.MAX_VALUE, stats.getExecutionMinTime() );
		assertEquals( 0, stats.getExecutionMaxTime() );
		assertEquals( 0, stats.getExecutionAvgTime() );

		stats.executed( 1000, 12 );

		assertEquals( 12, stats.getExecutionTotalTime() );
		assertEquals( 12, stats.getExecutionMinTime() );
		assertEquals( 12, stats.getExecutionMaxTime() );
		assertEquals( 12, stats.getExecutionAvgTime() );

		stats.executed( 200, 11 );

		assertEquals( 23, stats.getExecutionTotalTime() );
		assertEquals( 11, stats.getExecutionMinTime() );
		assertEquals( 12, stats.getExecutionMaxTime() );
		assertEquals( 11, stats.getExecutionAvgTime() );
		assertEquals( 11.5, stats.getExecutionAvgTimeAsDouble(), 0.1 );
	}
