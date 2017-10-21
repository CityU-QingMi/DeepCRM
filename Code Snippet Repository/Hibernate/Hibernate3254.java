	private static int getNextBatchSize(int batchSize) {
		if ( batchSize <= 10 ) {
			return batchSize - 1; //allow 9,8,7,6,5,4,3,2,1
		}
		else if ( batchSize / 2 < 10 ) {
			return 10;
		}
		else {
			return batchSize / 2;
		}
	}
