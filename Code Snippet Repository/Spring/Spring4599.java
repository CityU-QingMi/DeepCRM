	private int calculateCapacity(int neededCapacity) {
		Assert.isTrue(neededCapacity >= 0, "'neededCapacity' must >= 0");

		if (neededCapacity == CAPACITY_THRESHOLD) {
			return CAPACITY_THRESHOLD;
		}
		else if (neededCapacity > CAPACITY_THRESHOLD) {
			int newCapacity = neededCapacity / CAPACITY_THRESHOLD * CAPACITY_THRESHOLD;
			if (newCapacity > MAX_CAPACITY - CAPACITY_THRESHOLD) {
				newCapacity = MAX_CAPACITY;
			}
			else {
				newCapacity += CAPACITY_THRESHOLD;
			}
			return newCapacity;
		}
		else {
			int newCapacity = 64;
			while (newCapacity < neededCapacity) {
				newCapacity <<= 1;
			}
			return Math.min(newCapacity, MAX_CAPACITY);
		}
	}
