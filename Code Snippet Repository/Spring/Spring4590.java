	@Override
	public int indexOf(IntPredicate predicate, int fromIndex) {
		Assert.notNull(predicate, "'predicate' must not be null");

		if (fromIndex < 0) {
			fromIndex = 0;
		}
		else if (fromIndex >= this.writePosition) {
			return -1;
		}
		for (int i = fromIndex; i < this.writePosition; i++) {
			byte b = this.byteBuffer.get(i);
			if (predicate.test(b)) {
				return i;
			}
		}
		return -1;
	}
