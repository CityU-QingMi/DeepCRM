	public static void main(String... args) {
		int[] batchSizes = ArrayHelper.getBatchSizes( 32 );

		System.out.println( "Forward ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		for ( int i = 0; i < batchSizes.length; i++ ) {
			System.out.println( "[" + i + "] -> " + batchSizes[i] );
		}

		System.out.println( "Backward ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		for ( int i = batchSizes.length - 1; i >= 0; i-- ) {
			System.out.println( "[" + i + "] -> " + batchSizes[i] );
		}
	}
