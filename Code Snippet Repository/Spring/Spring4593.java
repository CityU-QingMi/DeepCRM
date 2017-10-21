	@Override
	public int lastIndexOf(IntPredicate predicate, int fromIndex) {
		Assert.notNull(predicate, "'predicate' must not be null");
		int i = Math.min(fromIndex, this.writePosition - 1);
		for (; i >= 0; i--) {
			byte b = this.byteBuffer.get(i);
			if (predicate.test(b)) {
				return i;
			}
		}
		return -1;
	}
