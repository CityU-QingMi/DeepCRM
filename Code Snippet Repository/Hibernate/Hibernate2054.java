	@Override
	public void cacheGetEnd(boolean hit) {
		assert cacheGetStart > 0 : "Unexpected call to cacheGetEnd; expecting cacheGetStart";

		if ( hit ) {
			cacheHitCount++;
			cacheHitTime += ( System.nanoTime() - cacheGetStart );
		}
		else {
			cacheMissCount++;
			cacheMissTime += ( System.nanoTime() - cacheGetStart );
		}
		cacheGetStart = -1;
	}
