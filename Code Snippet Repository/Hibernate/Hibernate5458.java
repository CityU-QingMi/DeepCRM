    @Test
	public void testBasicHiloAlgorithm() {
		// mimic an initialValue of 1 and increment of 20
		final long initialValue = 1;
		final long incrementSize = 2;

		// initialization
		IntegralDataTypeHolder lastSourceValue = makeHolder().initialize( 1 );
		IntegralDataTypeHolder upperLimit = lastSourceValue.copy().multiplyBy( incrementSize ).increment();
		IntegralDataTypeHolder value = upperLimit.copy().subtract( incrementSize );

		assertEquals( 1, lastSourceValue.makeValue().longValue() );
		assertEquals( 3, upperLimit.makeValue().longValue() );
		assertEquals( 1, value.makeValue().longValue() );

		value.increment();
		value.increment();

		assertFalse( upperLimit.gt( value ) );

		// at which point we would "clock over"
		lastSourceValue.increment();
		upperLimit = lastSourceValue.copy().multiplyBy( incrementSize ).increment();

		assertEquals( 2, lastSourceValue.makeValue().longValue() );
		assertEquals( 5, upperLimit.makeValue().longValue() );
		assertEquals( 3, value.makeValue().longValue() );
	}
