	@Override
	public void flushEnd(int numberOfEntities, int numberOfCollections) {
		assert flushStart > 0 : "Unexpected call to flushEnd; expecting flushStart";

		flushCount++;
		flushEntityCount += numberOfEntities;
		flushCollectionCount += numberOfCollections;
		flushTime += ( System.nanoTime() - flushStart );
		flushStart = -1;
	}
