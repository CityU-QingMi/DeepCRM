	public static int[] getBatchSizes(int maxBatchSize) {
		int batchSize = maxBatchSize;
		int n = 1;
		while ( batchSize > 1 ) {
			batchSize = getNextBatchSize( batchSize );
			n++;
		}
		int[] result = new int[n];
		batchSize = maxBatchSize;
		for ( int i = 0; i < n; i++ ) {
			result[i] = batchSize;
			batchSize = getNextBatchSize( batchSize );
		}
		return result;
	}
