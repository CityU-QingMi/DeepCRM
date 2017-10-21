	@Override
	public void partialFlushEnd(int numberOfEntities, int numberOfCollections) {
		assert partialFlushStart > 0 : "Unexpected call to partialFlushEnd; expecting partialFlushStart";

		partialFlushCount++;
		partialFlushEntityCount += numberOfEntities;
		partialFlushCollectionCount += numberOfCollections;
		partialFlushTime += ( System.nanoTime() - partialFlushStart );
		partialFlushStart = -1;
	}
